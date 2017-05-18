package eg.iti.shareit.ws.test;

import com.google.gson.reflect.TypeToken;
import eg.iti.shareit.common.util.JsonUtil;
import eg.iti.shareit.common.util.RestUtil;
import eg.iti.shareit.model.dto.UserDto;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mohamed_2 on 11/20/2015.
 */
public class WebServiceTest {


    RestUtil restUtil;
    JsonUtil jsonUtil = JsonUtil.getInstance();

    private static final Logger logger =  Logger.getLogger(WebServiceTest.class.getName());

    @Before
    public void init() {
        restUtil = RestUtil.getInstance("localhost", 8080, "shareit/rest/");
    }


    @Test
    public void userAuthTest()throws IOException {

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "eltntawy"));
        params.add(new BasicNameValuePair("password", "123"));

        Response response;



            String responseStr = restUtil.get("Auth", params);

            Type type = new TypeToken<Response>() {}.getType();

            response = (Response) jsonUtil.fromJson(responseStr, type);

            Assert.assertNotNull(response);

            Assert.assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());

            Assert.assertNotNull(response.getEntity());


            Assert.assertTrue(response.getEntity() instanceof UserDto);

            UserDto userDto = (UserDto) response.getEntity();



    }
}
