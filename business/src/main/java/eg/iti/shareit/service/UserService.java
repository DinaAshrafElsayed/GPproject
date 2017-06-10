package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.UserDao;
import eg.iti.shareit.model.dto.AddressDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.entity.AddressEntity;
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
    @EJB
    private AddressService addressService;
    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    public UserDto getUserByEmail(String email) throws ServiceException {
        try {
            UserEntity userEntity = userDao.getUserByEmail(email);
            if (userEntity != null) {
                UserDto userDto = mappingUtil.getDto(userEntity, UserDto.class);
                return userDto;
            } else {
                return null;
            }
        } catch (DatabaseRollbackException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
    }

    public void RegisterUser(UserDto user) throws ServiceException {
        try {
            user.setGender(genderService.getGender(user.getGender().getGender()));
            System.out.println("addressDto for user is : "+user.getAddress());
            AddressDto addressDto = addressService.getAddress(user.getAddress());
            System.out.println("addressDto is after getting it from database "+addressDto);
            user.setAddress(addressDto);
            UserEntity userEntity = mappingUtil.getEntity(user, UserEntity.class);
            System.out.println("user dto " + user);
            //AddressEntity addressEntity = mappingUtil.getEntity(addressDto, AddressEntity.class);
            //System.out.println("addressEntity is "+addressEntity);
            //userEntity.setAddress(addressEntity);
            boolean saved = userDao.saveUser(userEntity);
            System.out.println("saved : " + saved);
        } catch (DatabaseRollbackException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
    }
    public void updateUser(UserDto user) throws ServiceException{
        try {
            user.setGender(genderService.getGender(user.getGender().getGender()));
            System.out.println("addressDto for user is : "+user.getAddress());
            AddressDto addressDto = addressService.getAddress(user.getAddress());
            System.out.println("addressDto is after getting it from database "+addressDto);
            user.setAddress(addressDto);
            UserEntity userEntity = mappingUtil.getEntity(user, UserEntity.class);
            System.out.println("user dto " + user);
            boolean added=userDao.updateUser(userEntity);
            System.out.println("----------------------- Saved "+added);
            
        } catch (DatabaseRollbackException e) {
         logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
    }

    public UserDto findUser(String email, String password) throws ServiceException {
        try {
            UserEntity userEntity = userDao.findUser(email, password);
            if (userEntity != null) {
                UserDto userDto = mappingUtil.getDto(userEntity, UserDto.class);
                //userDto.setAddressDto(mappingUtil.getDto(userEntity.getAddress(), AddressDto.class));
                System.out.println("userDto is "+userDto);
                return userDto;
            }
            return null;
        } catch (DatabaseRollbackException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        }
    }
}
