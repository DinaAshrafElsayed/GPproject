/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.NotificationEntity;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless(mappedName = "NotificationDaoImpl")
public class NotificationDaoImpl extends GenericDaoImpl<NotificationEntity> implements NotificationDao {

    public NotificationDaoImpl() {
        super(NotificationEntity.class);
    }

    @Override
    public List<NotificationEntity> getNotSeenNotifications(UserEntity userEntity) throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("Select n From NotificationEntity n where n.toUser =" + userEntity.getId() + " and n.seen=0 ");
        List<NotificationEntity> notificationEntities = query.getResultList();
        if (notificationEntities != null) {
            return notificationEntities;
        }
        return null;
    }

    @Override
    public List<NotificationEntity> getSeenNotifications(UserEntity userEntity) throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("Select n From NotificationEntity n where n.toUser =" + userEntity.getId() + " and n.seen!=0");
        List<NotificationEntity> notificationEntities = query.getResultList();
        if (notificationEntities != null) {
            return notificationEntities;
        }
        return null;
    }

    @Override
    public void saveNotification(NotificationEntity notificationEntity) throws DatabaseRollbackException {
        save(notificationEntity);
    }

    @Override
    public void setNotificationAsRead(BigDecimal id) throws DatabaseRollbackException {
        NotificationEntity notificationEntity = get(id);
        notificationEntity.setSeen(BigInteger.valueOf(1));
        update(notificationEntity);
    }

}
