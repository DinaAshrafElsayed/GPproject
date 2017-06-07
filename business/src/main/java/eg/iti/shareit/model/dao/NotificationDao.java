/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.model.entity.NotificationEntity;
import eg.iti.shareit.model.entity.UserEntity;

/**
 *
 * @author Adel Zaid
 */
public interface NotificationDao extends GenericDao<NotificationEntity> {

    NotificationEntity getNotification(UserEntity userEntity) throws DatabaseRollbackException;
}
