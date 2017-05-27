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
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActivities() {
        Response response;
        try {
            List<ActivityDto> allActivities = activityService.getAllActivities();
            if (allActivities != null) {
                response = Response.ok().entity(allActivities).build();
            } else {
                response = Response.status(Response.Status.NOT_FOUND).entity("User has no activities").build();
            }
            logger.info("user returned successfully : " + allActivities.get(0));
        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "service exception occurred", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        }
        return response;
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActivity(@QueryParam("id") int id) {
        Response response;
        try {
            ActivityDto activity = activityService.getActivity(id);
            response = Response.ok().entity(activity).build();
            logger.log(Level.INFO, "user returned successfully : {0}", activity.getItem());

        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "service exception occurred", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        }
        return response;
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteActivity(@QueryParam("id") int id) {
        Response response;
        try {
            activityService.deleteActivity(id);
            response = Response.status(Response.Status.OK).entity("User Deleted").build();

        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "Service Exception occurred", e);
            response = Response.ok().status(Response.Status.NOT_FOUND).entity("Specified id is not found!").build();
        }
        return response;
    }
}
