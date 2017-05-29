/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.enums.StatusEnum;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.NotificationDto;
import eg.iti.shareit.model.dto.StatusDto;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.entity.ItemEntity;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import static jdk.nashorn.internal.runtime.PropertyMap.diff;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 *
 * @author Adel Zaid
 */
@Stateless(mappedName = "ActivityDaoImplBTM")
@TransactionManagement(TransactionManagementType.BEAN)
public class ActivityDaoImplBTM extends GenericDaoImpl<ActivityEntity> implements ActivityDaoBTM {

    public ActivityDaoImplBTM() {
        super(ActivityEntity.class);
    }

    @Inject
    UserTransaction userTransaction;
    @Inject
    UserDao userDao;
    @Inject
    ActivityDao activityDao;
    @Inject
    ItemDao itemDao;

    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    @Override
    public void acceptRequest(ActivityDto activityDto) throws DatabaseRollbackException {
        try {
            userTransaction.begin();

            //make the status accepted for the request.
            activityDto.setStatus(StatusEnum.ACCEPTED.getStatus());
            ActivityEntity activityEntity = mappingUtil.getEntity(activityDto, ActivityEntity.class);
            activityDao.update(activityEntity);

            //We need to get the points from hamada and transfer it to mervat
            //So I get the points of the item because if hamada doesn't have enough points he'll not be able to make the request.
            int fromPoints = activityDto.getItem().getPoints();
            int toPoints = activityDto.getToUser().getPoints() + fromPoints;
            UserEntity mervatEntity = mappingUtil.getEntity(activityDto.getToUser(), UserEntity.class);
            mervatEntity.setPoints(new BigInteger(String.valueOf(toPoints)));
            userDao.update(mervatEntity);

            //We need to deduct points from hamada's account
            UserEntity hamadaEntity = mappingUtil.getEntity(activityDto.getFromUser(), UserEntity.class);
            hamadaEntity.setPoints(new BigInteger(String.valueOf(hamadaEntity.getPoints().intValue() - fromPoints)));
            userDao.update(hamadaEntity);

            //Lock the item by making the is_available=0 which means unavailable
            activityDto.getItem().setIsAvailable(0);
            ItemEntity itemEntity = mappingUtil.getEntity(activityDto.getItem(), ItemEntity.class);
            itemDao.update(itemEntity);
            userTransaction.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            try {
                userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                Logger.getLogger(ActivityDaoImplBTM.class.getName()).log(Level.SEVERE, null, ex);
            }
            throw new DatabaseRollbackException("Cannot save this activity");
        }
    }

    @Override
    public NotificationDto getNotification(int id) {

        ActivityEntity activityEntity = activityDao.get(new BigDecimal(id));
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setPoints(activityEntity.getItem().getPoints().intValue());
        notificationDto.setMeetingPoint(activityEntity.getMeetingPoint());

        //The interval should be the differnce between the two dates but i find it hard to get the difference, So i calculated the days instead
        long days = activityEntity.getTimeTo().getTime() - activityEntity.getTimeFrom().getTime();
        long days1 = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
        notificationDto.setDays((int) days1);
        return notificationDto;
    }
}
