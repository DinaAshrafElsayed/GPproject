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


}
