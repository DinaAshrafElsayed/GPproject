package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.model.entity.UserEntity;


/**
 * Created by Mohamed on 2015/07/04.
 */
public interface UserDao extends GenericDao<UserEntity> {

    public UserEntity getUserByEmail(String email) throws DatabaseRollbackException;

}
