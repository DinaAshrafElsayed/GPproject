/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.ws;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.service.ItemService;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Yousef
 */
@Path("/Item")
public class ItemWS {
    
    @EJB
    ItemService itemService;
    
    private static final Logger logger = Logger.getLogger(ItemWS.class.getName());
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {


        List<ItemDto> items = null;
        Response response ;
        try {
            items = itemService.getAllItems();

            response =  Response.ok().entity(items).build();

            logger.info("items returned successfully : "+items);

        } catch (ServiceException e) {
            logger.log(Level.SEVERE,"service exception occurred",e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        }catch (Exception e) {
            logger.log(Level.SEVERE,"unexpected error",e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error has been occurred please try again later").build();
        }

        return response;
    }
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchItems(@QueryParam("name")String name,@QueryParam("category")int categoryId){
        List<ItemDto> items = null;
        Response response;
        try {
            items = itemService.searchItems(name, categoryId);
            System.out.println("name parameter value : "+name);
            System.out.println("itemsss : "+items.size());
            response = Response.ok().entity(items).build();
            logger.info("items returned successfully : "+items);
        } catch (ServiceException ex) {
            logger.log(Level.SEVERE,"service exception occurred",ex);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        }catch (Exception e) {
            logger.log(Level.SEVERE,"unexpected error",e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error has been occurred please try again later").build();
        }
        return response;
    }
    
    
    @GET
    @Path("/isAvailable")
    @Produces(MediaType.APPLICATION_JSON)
    public Response isAvailable(@QueryParam("id")int itemId){
        Response response;
        try {
            boolean isItemAvailable = itemService.isItemAvailable(itemId);
             response = Response.ok().entity(isItemAvailable).build();
        } catch (ServiceException ex) {
            logger.log(Level.SEVERE,"service exception occurred",ex);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        }catch (Exception e) {
            logger.log(Level.SEVERE,"unexpected error",e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error has been occurred please try again later").build();
        }
        return response;
    }
}