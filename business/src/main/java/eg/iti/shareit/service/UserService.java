package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.AddressDao;
import eg.iti.shareit.model.dao.GenderDao;
import eg.iti.shareit.model.dao.UserDao;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.MappingUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mohamed_2 on 11/14/2015.
 */
@Stateless
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @EJB
    private UserDao userDao;
    @EJB
    private GenderService genderService;

    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    public UserDto getUserByEmail(String email) throws ServiceException {
        try {
            UserEntity userEntity = userDao.getUserByEmail(email);
            UserDto userDto = mappingUtil.getDto(userEntity, UserDto.class);
            return userDto;
        } catch (DatabaseException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
    }

    public void RegisterUser(UserDto user) {
        user.setGender(genderService.getGender(user.getGender().getGender()));
        UserEntity userEntity = mappingUtil.getEntity(user, UserEntity.class);
        userDao.save(userEntity);
    }
}
