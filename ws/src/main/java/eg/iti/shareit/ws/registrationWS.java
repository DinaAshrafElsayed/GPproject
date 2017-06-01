package eg.iti.shareit.ws;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.GenderDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.util.HashingUtil;
import eg.iti.shareit.model.util.ImageUtil;
import eg.iti.shareit.service.UserService;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * Created by Mohamed_2 on 11/14/2015.
 */
@Path("/upload")
public class registrationWS {

    @EJB
    UserService userService;

    private static final Logger logger = Logger.getLogger(registrationWS.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("multipart/form-data")
    public Response Register(MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        // Get file data to save
        List<InputPart> inputParts = uploadForm.get("attachment");
        String fileName = "";
        Response response;
        try {
            for (InputPart inputPart : inputParts) {
                MultivaluedMap<String, String> header = inputPart.getHeaders();
                ImageUtil imageUtil = new ImageUtil();
                fileName = imageUtil.getFileName(header);
                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class,
                        null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                // constructs upload file path and saves it
                fileName = "/home/user/profile/" + fileName;
                imageUtil.writeFile(bytes, fileName);
            }
            UserDto userDto = buildUser(input, fileName);
            userService.RegisterUser(userDto);
            response = Response.status(200).entity("user Registered : " + userDto)
                    .build();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO exception occurred", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("IO exception occurred please try again later").build();
        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "service exception occurred", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Service exception occurred please try again later").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "unexpected error", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unexpected error has been occurred please try again later").build();
        }
        return response;
    }

    private UserDto buildUser(MultipartFormDataInput input, String fileName) throws IOException {
        //remains saving user address needs to fix database
        UserDto userDto = new UserDto();
        userDto.setUsername(input.getFormDataPart("username", String.class, null));
        userDto.setEmail(input.getFormDataPart("email", String.class, null));
        String password = input.getFormDataPart("password", String.class, null);
        HashingUtil hashingUtil = new HashingUtil();
        userDto.setPassword(hashingUtil.getHashedPassword(password));
        userDto.setImageUrl(fileName);
        userDto.setPoints(100);
        GenderDto genderDto = new GenderDto();
        genderDto.setGender(input.getFormDataPart("gender", String.class, null));
        userDto.setGender(genderDto);
        return userDto;
    }
}