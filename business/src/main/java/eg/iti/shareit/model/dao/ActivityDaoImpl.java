/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.enums.StatusEnum;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.entity.StatusEntity;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Adel Zaid
 */
@Stateless(mappedName = "ActivityDaoImpl")
public class ActivityDaoImpl extends GenericDaoImpl<ActivityEntity> implements ActivityDao {

    @EJB
    ActivityDao activityDao;
    @Inject
    StatusDao statusDao;
    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    public ActivityDaoImpl() {
        super(ActivityEntity.class);
    }

    @Override
    public void saveActivity(ActivityEntity activityEntity) throws DatabaseRollbackException {
        StatusEntity statusEntitiy = statusDao.get(activityEntity.getStatus().getId());
        if (statusEntitiy == null) {
            activityEntity.getStatus().setId(null);
            statusDao.save(activityEntity.getStatus());
        }
        save(activityEntity);

    }

    @Override
    public String cancelRequest(int id) throws DatabaseRollbackException {
        try {
            //Get object
            //Set Activity Deleted
            ActivityEntity activityEntity = activityDao.get(BigDecimal.valueOf(id));
            if (activityEntity.getActivityDeleted() == 1) {
                return "Deleted";
            } else if (activityEntity.getStatus().getStatus().equals("Pending")) {

                activityEntity.setActivityDeleted((short) 1);
                activityDao.update(activityEntity);
                return "Pending";
            } else if (activityEntity.getStatus().getStatus().equals("Declined")) {
                return "Declined";
            } else {
                return "Accepted";
            }
        } catch (Exception e) {
            Logger.getLogger(ActivityDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseRollbackException("Cannot Cancel The request");
        }
    }

    @Override
    public String declineRequest(int id) throws DatabaseRollbackException {
        try {
            ActivityEntity activityEntity = activityDao.get(BigDecimal.valueOf(id));
            if (activityEntity.getActivityDeleted() == 1) {
                return "Deleted";
            } else if (activityEntity.getStatus().getStatus().equals("Declined")) {
                return "Already Declined";
            } else {

                StatusEntity statusEntity = mappingUtil.getEntity(StatusEnum.DECLINED.getStatus(), StatusEntity.class);
                activityEntity.setStatus(statusEntity);
                activityEntity.setActivityDeleted((short) 1);
                activityDao.update(activityEntity);
                return "Declined";
            }
        } catch (Exception e) {
            Logger.getLogger(ActivityDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseRollbackException("Cannot Cancel The request");
        }
    }

    @Override
    public List<ActivityEntity> getAllActivities() throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("Select a From ActivityEntity a where a.status.status = 'Pending'");
        List<ActivityEntity> activityEntities = query.getResultList();
        if (activityEntities != null && activityEntities.size() == 1) {
            return activityEntities;
        }
        return null;
    }

}
