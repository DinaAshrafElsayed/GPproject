/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.entity.BorrowStateEntity;
import eg.iti.shareit.model.entity.UserEntity;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Adel Zaid
 */
public class BorrowStateImpl extends GenericDaoImpl<BorrowStateEntity> implements BorrowStateDao {

    @EJB
    ActivityDao activityDao;

    public BorrowStateImpl(Class<BorrowStateEntity> type) {
        super(type);
    }

    @Override
    public void handleBorrowingDueDate(UserEntity userEntity) throws DatabaseRollbackException {
        List<ActivityEntity> activityEntities = activityDao.getAvtivityOfUser(userEntity);
        for (ActivityEntity activityEntity : activityEntities) {
            if (activityEntity.getTimeTo().equals(new Date()) || activityEntity.getTimeTo().after(new Date())) {
                BorrowStateEntity borrowStateEntity = new BorrowStateEntity();
                borrowStateEntity.setActivity(activityEntity);
                borrowStateEntity.setIsBack(BigInteger.valueOf(0));
                save(borrowStateEntity);
            }
        }
    }
}
