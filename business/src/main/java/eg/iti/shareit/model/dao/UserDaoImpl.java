package eg.iti.shareit.model.dao;


import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Mohamed on 2015/07/04.
 */
@Stateless(mappedName = "UserDaoImpl")
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {

    @Override
    public UserEntity authenticateByEmail(String email, String password) throws DatabaseRollbackException {
        Query query = entityManager.createQuery("Select u From UserEntity u where u.email = :email AND u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        try {
            List<UserEntity> userList = query.getResultList();
            if (userList != null && userList.size() == 1) {
                return userList.get(0);
            } else {
                throw new DatabaseRollbackException("UserEntity with email <" + email + "> Authentication Failed");
            }
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }

    }

    @Override
    public UserEntity authenticateByUsername(String username, String password) throws DatabaseRollbackException {
        Query query = entityManager.createQuery("Select u From UserEntity u where u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            List<UserEntity> userList = query.getResultList();


            if (userList != null && userList.size() == 1) {
                return userList.get(0);
            } else {
                throw new DatabaseRollbackException("UserEntity with username <" + username + "> Authentication Faild");
            }

        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) throws DatabaseRollbackException {
        Query query = entityManager.createQuery("Select u From UserEntity u where u.email = :email");
        query.setParameter("email", email);

        try {
            List<UserEntity> userList = query.getResultList();
            if (userList != null && userList.size() == 1) {
                return userList.get(0);
            } else {
                throw new DatabaseRollbackException("UserEntity with email <" + email + "> Not Found");
            }
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }


}
