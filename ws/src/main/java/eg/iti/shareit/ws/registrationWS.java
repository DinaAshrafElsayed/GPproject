package eg.iti.shareit.ws;

import eg.iti.shareit.model.dto.GenderDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.UserService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
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
    public Response uploadUserImage(MultipartFormDataInput input) {

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

        // Get file data to save
        List<InputPart> inputParts = uploadForm.get("attachment");

        for (InputPart inputPart : inputParts) {
            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                String fileName = getFileName(header);

                // convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class,
                        null);

                byte[] bytes = IOUtils.toByteArray(inputStream);
                // constructs upload file path and saves it
                fileName = "/home/user/profile/" + fileName;
                writeFile(bytes, fileName);

                UserDto userDto = new UserDto();
                userDto.setUsername(input.getFormDataPart("username", String.class, null));
                userDto.setEmail(input.getFormDataPart("email", String.class, null));
                userDto.setPassword(input.getFormDataPart("password", String.class, null));
                userDto.setImageUrl(fileName);
                userDto.setPoints(100);
                GenderDto genderDto = new GenderDto();
                genderDto.setGender(input.getFormDataPart("gender", String.class, null));
                userDto.setGender(genderDto);

                //save url to database
                return Response.status(200).entity("Uploaded file name : " + fileName)
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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
