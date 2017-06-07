/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.entity.NotificationEntity;

/**
 *
 * @author Adel Zaid
 */
public interface ActivityDaoBTM extends GenericDao<ActivityEntity> {

    void acceptRequest(ActivityEntity activityEntity) throws DatabaseRollbackException;

    void saveNotification(NotificationEntity notificationEntity) throws DatabaseRollbackException;
}
