package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.HashingUtil;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;
import javax.ejb.EJB;

/**
 * Created by Mohamed on 2015/07/04.
 */
@Stateless(mappedName = "UserDaoImpl")
public class UserDaoImpl extends GenericDaoImpl<UserEntity> implements UserDao {

    @EJB
    private HashingUtil hashingUtil;

    public UserDaoImpl() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity getUserByEmail(String email) throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("Select u From UserEntity u where u.email = :email");
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

    @Override
    public UserEntity findUser(String email, String password) throws DatabaseRollbackException {
        String hashedPassword = hashingUtil.getHashedPassword(password);
        Query query = getEntityManager().createQuery("Select u From UserEntity u where u.email = :email and u.password = :password");
        query.setParameter("email", email).setParameter("password", hashedPassword);
        try {
            List<UserEntity> userList = query.getResultList();
            if (userList != null && userList.size() == 1) {
                return userList.get(0);
            } else {
                return null;
            }
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public boolean saveUser(UserEntity user) throws DatabaseRollbackException {
        try {
            getEntityManager().persist(user);
            return true;
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());

        }
    }
}
