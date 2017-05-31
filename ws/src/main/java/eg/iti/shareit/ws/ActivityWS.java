/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.ws;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.NotificationDto;
import eg.iti.shareit.service.ActivityService;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    @POST
    @Path("/declineRequest")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteActivity(@QueryParam("id") int id) {
        Response response;
        try {
            String declineRequest = activityService.declineRequest(id);
            switch (declineRequest) {
                case "Deleted":
                    response = Response.ok().entity("Request is already deleted!").build();
                    break;
                case "Already Declined":
                    response = Response.status(Response.Status.OK).entity("Request is already Declined").build();
                    break;
                default:
                    response = Response.status(Response.Status.OK).entity("Request has been successfully Declined").build();
                    break;
            }

        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "Service Exception occurred", e);
            response = Response.ok().status(Response.Status.NOT_FOUND).entity("Specified id is not found!").build();
        }
        return response;
    }

    @POST
    @Path("/acceptRequest")
    public Response acceptRequest(@QueryParam("id") int id) {
        Response response;
        try {
            ActivityDto activityDto = activityService.getActivity(id);
            activityService.acceptRequest(activityDto);
            response = Response.status(Response.Status.OK).entity("Request Accepted").build();
        } catch (ServiceException ex) {
            logger.log(Level.SEVERE, "Service Exception occurred", ex);
            response = Response.ok().status(Response.Status.NOT_FOUND).entity("Specified id is not found!").build();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Unexpected Error occured", ex);
            response = Response.ok().status(Response.Status.INTERNAL_SERVER_ERROR).entity("There was a problem saving this transaction in database!").build();
        }
        return response;
    }

    @GET
    @Path("/getNotification")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@QueryParam("id") int id) {
        Response response;
        try {
            NotificationDto notification = activityService.getNotification(id);
            response = Response.ok().entity(notification).build();
            logger.info("Notification returned successfully : " + notification);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected Error occured", e);
            response = Response.ok().status(Response.Status.INTERNAL_SERVER_ERROR).entity("There was a problem getting this Notification").build();
        }
        return response;
    }

    @POST
    @Path("/requestItem")
    @Produces(MediaType.TEXT_HTML)
    public Response requestItem(@FormParam("item") int itemId,
            @FormParam("from") int fromUserId,
            @FormParam("to") int toUserId,
            @FormParam("timeTo") Date timeTo,
            @FormParam("meetingPoint") String meetingPoint) {
        Response response;
        boolean result;
        try {

            result = activityService.requestItem(itemId, fromUserId, toUserId, timeTo, meetingPoint);
            if (result) {
                response = Response.ok().entity("item requested successfully").build();
            } else {
                response = Response.ok().entity("item is not available").build();
            }
            logger.info("request done successfully " + result);
        } catch (ServiceException ex) {
            logger.log(Level.SEVERE, "Unexpected Error occured", ex);
            response = Response.ok().status(Response.Status.INTERNAL_SERVER_ERROR).entity("There was a problem requesting the item").build();
        }

        return response;
    }

    @POST
    @Path("/cancelRequest")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cancelRequest(@QueryParam("id") int id) {
        boolean itemStatus;
        Response response;
        String cancelRequest;
        try {
            cancelRequest = activityService.cancelRequest(id);
            if (cancelRequest.equals("Pending")) {
                response = Response.ok().entity("Request has been canceled!").build();
            } else if (cancelRequest.equals("Declined")) {
                response = Response.ok().entity("Request is already declined!").build();
            } else if (cancelRequest.equals("Accepted")) {
                response = Response.ok().entity("Request is already accepted!").build();
            } else {
                response = Response.ok().entity("Request is already deleted!").build();
            }
        } catch (ServiceException ex) {
            logger.log(Level.SEVERE, "service exception occurred", ex);
            response = Response.status(Response.Status.NOT_FOUND).entity("Specified id is not found!").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "unexpected error", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error has been occurred please try again later").build();
        }
        return response;
    }
}
