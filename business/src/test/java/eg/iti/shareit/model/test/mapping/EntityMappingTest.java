package eg.iti.shareit.model.test.mapping;


import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.entity.TUserEntity;
import eg.iti.shareit.model.util.MappingUtil;
import eg.iti.shareit.service.UserService;
import org.junit.Assert;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Mohamed_2 on 11/13/2015.
 */
public class EntityMappingTest {


    private Mapper mapper;

    private MappingUtil mappingUtil;


    @Before
    public void init() throws NamingException {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        List<String> configFileList = new ArrayList<String>();
        configFileList.add("dozer-mapping.xml");

        dozerBeanMapper.setMappingFiles(configFileList);

        mapper = (Mapper) dozerBeanMapper;


    }

    @Test
    public void UserEntityShouldMappedToUserDto() {
        try {
            UserService userService = new UserService();
            UserDto userDto = userService.getUserByEmail("yousef");

           

        } catch (ServiceException e) {
          
           
        }catch (Exception e) {
            
            
        }
        TUserEntity userEntity = new TUserEntity();

        String name = "Hamada";

        userEntity.setUsername(name);

        UserDto UserDto = mapper.map(userEntity, UserDto.class);

        Assert.assertNotNull(UserDto);

        Assert.assertNotNull(UserDto.getUsername());
        Assert.assertEquals(userEntity.getUsername(), UserDto.getUsername());

    }

    @Test
    public void UserDtoShouldMappedToUserEntity() {

        UserDto UserDto = new UserDto();

        String name = "Hamada";

        UserDto.setUsername(name);

        TUserEntity userEntity = mapper.map(UserDto, TUserEntity.class);

        Assert.assertNotNull(userEntity);

        Assert.assertEquals(userEntity.getUsername(), UserDto.getUsername());

    }


}