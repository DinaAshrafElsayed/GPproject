/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.NotificationEntity;
import eg.iti.shareit.model.entity.UserEntity;
import javax.ejb.Stateless;

@Stateless(mappedName = "NotificationDaoImpl")
public class NotificationDaoImpl extends GenericDaoImpl<NotificationEntity> implements NotificationDao {

    public NotificationDaoImpl() {
        super(NotificationEntity.class);
    }

    @Override
    public NotificationEntity getNotification(UserEntity userEntity) throws DatabaseRollbackException {
        return null;
    }

}
