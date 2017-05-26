/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.ws;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.service.ActivityService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Adel Zaid
 */
@Path("/Activity")
public class ActivityWS {

    @Inject
    ActivityService activityService;

    private static final Logger logger = Logger.getLogger(ActivityWS.class.getName());

    @GET
//    @Path("/method")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllActivities() {
        Response response;
        try {
            List<ActivityDto> allActivities = activityService.getAllActivities();
            response = Response.ok().entity(allActivities).build();
            logger.info("user returned successfully : " + allActivities.get(0));
        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "service exception occurred", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        }
        return "Activities found!";
    }
}
