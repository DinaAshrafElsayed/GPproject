package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.UserDao;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.MappingUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by Mohamed_2 on 11/14/2015.
 */
@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;



    public UserDto authenticateByEmail(String email, String password) throws ServiceException {

        UserEntity userEntity = null;
        try {
            userEntity = userDao.authenticateByEmail(email, password);

            UserDto userDto = mappingUtil.getDto(userEntity, UserDto.class);

            return userDto;

        } catch (DatabaseException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public UserDto authenticateByUsername(String username, String password) throws ServiceException {

        UserEntity userEntity = null;
        try {
            userEntity = userDao.authenticateByUsername(username, password);

            UserDto userDto = mappingUtil.getDto(userEntity, UserDto.class);

            return userDto;
        } catch (DatabaseException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public UserDto getUserByEmail(String email) throws ServiceException {
        try {
            UserEntity userEntity = userDao.getUserByEmail(email);
            UserDto userDto = mappingUtil.getDto(userEntity, UserDto.class);

            return userDto;
        }catch (DatabaseException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


}
