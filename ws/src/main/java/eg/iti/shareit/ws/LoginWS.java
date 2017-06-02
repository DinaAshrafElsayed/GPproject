/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.ws;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.util.SessionIdUtil;
import eg.iti.shareit.service.UserService;
import eg.iti.shareit.service.UserTrackingService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 *
 * @author Dina Ashraf
 */
@Path("/Login")
public class LoginWS {

    @EJB
    UserService userService;
    @EJB
    UserTrackingService trackingService;
    private static final Logger logger = Logger.getLogger(LoginWS.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(@FormParam("email") String email, @FormParam("password") String password) {
        UserDto userDto = null;
        Response response;
        try {
            userDto = userService.findUser(email, password);
            SessionIdUtil sessionIdUtil = new SessionIdUtil();
            String key =  sessionIdUtil.nextSessionId();
            NewCookie usercookie = new NewCookie("shareitCookie",key);
            if (userDto != null) {
                trackingService.addUser(key, userDto);
                response = Response.ok().cookie(usercookie).entity(userDto).build();
                logger.info("user logged successfully : " + userDto);
            } else {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Wrong email or password").build();
                logger.info("wrong username or password: " + userDto);
            }
        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "service exception occurred", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "unexpected error", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error has been occurred please try again later").build();
        }
        return response;
    }
}
