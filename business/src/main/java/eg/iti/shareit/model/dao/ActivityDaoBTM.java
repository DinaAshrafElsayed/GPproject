/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.NotificationDto;
import eg.iti.shareit.model.entity.ActivityEntity;

/**
 *
 * @author Adel Zaid
 */
public interface ActivityDaoBTM extends GenericDao<ActivityEntity> {

    void acceptRequest(ActivityDto activityDto) throws DatabaseRollbackException;

    NotificationDto getNotification(int id) throws DatabaseRollbackException;
}
