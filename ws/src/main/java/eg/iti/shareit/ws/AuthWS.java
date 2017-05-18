package eg.iti.shareit.ws;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Mohamed_2 on 11/14/2015.
 */
@Path("/Auth")
public class AuthWS {

    @Inject
    UserService userService;

    private static final Logger logger =  Logger.getLogger(AuthWS.class.getName());

    @GET
    @Produces("application/json")
    public Response getUser(@QueryParam("username") String username, @QueryParam("password") String password) {


        UserDto userDto = null;
        Response response ;
        try {
            userDto = userService.authenticateByUsername(username, password);

            response =  Response.ok().entity(userDto).build();

            logger.info("user returned successfully");

        } catch (ServiceException e) {

            logger.log(Level.SEVERE,"unexpected error",e);
           response = Response.status(Status.EXPECTATION_FAILED).entity("Unexpected error has been occurred please try again later").build();
        }

        return response;
    }
}
