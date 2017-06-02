/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author Dina Ashraf
 */
@Stateless
public class ImageUtil {
     public String getFileName(MultivaluedMap<String, String> header) {

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
    public void writeFile(byte[] content, String filename) throws IOException {
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
