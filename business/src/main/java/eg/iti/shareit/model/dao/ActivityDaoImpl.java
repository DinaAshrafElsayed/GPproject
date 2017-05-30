/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.entity.StatusEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Adel Zaid
 */
@Stateless(mappedName = "ActivityDaoImpl")
public class ActivityDaoImpl extends GenericDaoImpl<ActivityEntity> implements ActivityDao {

    @Inject
    StatusDao statusDao;
    public ActivityDaoImpl() {
        super(ActivityEntity.class);
    }

    @Override
    public void saveActivity(ActivityEntity activityEntity) throws DatabaseRollbackException {
        StatusEntity statusEntitiy = statusDao.get(activityEntity.getStatus().getId());
        if(statusEntitiy == null){
            activityEntity.getStatus().setId(null);
           statusDao.save(activityEntity.getStatus());
        }
        save(activityEntity);
        
        
    }

    
    

}
