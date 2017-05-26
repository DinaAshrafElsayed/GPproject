package eg.iti.shareit.ws;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.UserService;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Created by Mohamed_2 on 11/14/2015.
 */
@Path("/Auth")
public class AuthWS {

    @Inject
    UserService userService;

    private static final Logger logger = Logger.getLogger(AuthWS.class.getName());

    @GET

    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@QueryParam("email") String email) {

        UserDto userDto = null;
        Response response;
        try {
            userDto = userService.getUserByEmail(email);

            response = Response.ok().entity(userDto).build();

            logger.info("user returned successfully : " + userDto);

        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "service exception occurred", e);
            response = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "unexpected error", e);
            response = Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unexpected error has been occurred please try again later").build();
        }
        return "fkldlsjfkl";
    }
}
