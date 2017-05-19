package eg.iti.shareit.model.test.mapping;


import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.MappingUtil;
import org.junit.Assert;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

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

        UserEntity userEntity = new UserEntity();

        String name = "Hamada";

        userEntity.setName(name);

        UserDto UserDto = mapper.map(userEntity, UserDto.class);

        Assert.assertNotNull(UserDto);

        Assert.assertNotNull(UserDto.getName());
        Assert.assertEquals(userEntity.getName(), UserDto.getName());

    }

    @Test
    public void UserDtoShouldMappedToUserEntity() {

        UserDto UserDto = new UserDto();

        String name = "Hamada";

        UserDto.setName(name);

        UserEntity userEntity = mapper.map(UserDto, UserEntity.class);

        Assert.assertNotNull(userEntity);

        Assert.assertEquals(userEntity.getName(), UserDto.getName());

    }


}
