package eg.iti.shareit.ws;

import eg.iti.shareit.model.dto.AddressDto;
import eg.iti.shareit.model.dto.CityDto;
import eg.iti.shareit.model.dto.CountryDto;
import eg.iti.shareit.model.dto.GenderDto;
import eg.iti.shareit.model.dto.StateDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.UserService;
import java.io.File;
import java.io.FileOutputStream;
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
@Path("/register")
public class RegistrationWS {

    @EJB
    UserService userService;

    private static final Logger logger = Logger.getLogger(RegistrationWS.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("multipart/form-data")
    public Response RegisterUser(MultipartFormDataInput input) {
        try {
            String fileName = SaveUserImage(input);
            UserDto user = buildUserDto(input, fileName);
            userService.RegisterUser(user);
            return Response.status(200).entity("User registered and image saved at : " + fileName)
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(RegistrationWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private UserDto buildUserDto(MultipartFormDataInput input, String fileName) throws IOException {
        UserDto userDto = new UserDto();
        userDto.setUsername(input.getFormDataPart("username", String.class, null));
        userDto.setEmail(input.getFormDataPart("email", String.class, null));
        userDto.setPassword(input.getFormDataPart("password", String.class, null));
        userDto.setImageUrl(fileName);
        userDto.setPoints(100);
        GenderDto genderDto = new GenderDto();
        genderDto.setGender(input.getFormDataPart("gender", String.class, null));
        userDto.setGender(genderDto);
//        AddressDto addressDto = new AddressDto();
//        CityDto cityDto = new CityDto();
//        cityDto.setCity(input.getFormDataPart("city", String.class, null));
//        CountryDto countryDto  = new CountryDto();
//        countryDto.setCountry(input.getFormDataPart("country", String.class, null));
//        StateDto stateDto = new StateDto();
//        stateDto.setState(input.getFormDataPart("state", String.class, null));
//        addressDto.setCity(cityDto);
//        addressDto.setCountry(countryDto);
//        addressDto.setState(stateDto);
//        userDto.set
        return userDto;
    }

    private String SaveUserImage(MultipartFormDataInput input) {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        // Get file data to save
        List<InputPart> inputParts = uploadForm.get("attachment");
        String fileName = "";
        for (InputPart inputPart : inputParts) {
            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);
                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class,
                        null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                // constructs upload file path and saves it
                fileName = "/home/user/profile/" + fileName;
                writeFile(bytes, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    // Utility method
    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("not exist > " + file.getAbsolutePath());
            file.createNewFile();
        }
        try (FileOutputStream fop = new FileOutputStream(file)) {
            fop.write(content);
            fop.flush();
        }
    }
}
