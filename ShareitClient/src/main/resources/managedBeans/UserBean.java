/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.GenderDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.util.HashingUtil;
import eg.iti.shareit.service.UserService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Dina Ashraf
 */
@ManagedBean(name = "user" , eager = true)
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private HashingUtil hashingUtil;

    @EJB
    UserService userService;
    private GenderDto genderDto;
    private UserDto userDto;
    private Part file;
    private String imageUrl = "";

    public String register() {
        try {
            String password = userDto.getPassword();
            userDto.setPassword(hashingUtil.getHashedPassword(password));
            userDto.setImageUrl(imageUrl);
            userDto.setPoints(100);
            userDto.setGender(getGenderDto());
            userService.RegisterUser(userDto);
            return "";
        } catch (ServiceException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void save() {
        
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File("E:\\ITI\\GPproject\\", Paths.get(file.getSubmittedFileName()).getFileName().toString()).toPath());
            imageUrl = "E:\\ITI\\GPproject\\" + Paths.get(file.getSubmittedFileName()).getFileName().toString();
        } catch (IOException e) {
            // Show faces message
            FacesMessage facesMessage = new FacesMessage("error uploading image");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, facesMessage);
        }
    }

    public String changeLang() {

        String locale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
        if (locale.equals("en")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("ar", "EG"));
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en", "US"));
        }

        return null;
    }

    /**
     * @return the userDto
     */
    public UserDto getUserDto() {
        return userDto;
    }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @param userDto the userDto to set
     */
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the genderDto
     */
    public GenderDto getGenderDto() {
        return genderDto;
    }

    /**
     * @param genderDto the genderDto to set
     */
    public void setGenderDto(GenderDto genderDto) {
        this.genderDto = genderDto;
    }

}
