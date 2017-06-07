/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Dina Ashraf
 */
@ManagedBean(name = "registerBean", eager = true)
@RequestScoped
public class RegistrationBean implements Serializable {

    @EJB
    private HashingUtil hashingUtil;

    @EJB
    UserService userService;

    private String gender;
    private String userName;
    private String email;
    private String password;
    private Part file;
    private String imageUrl = "";

    public RegistrationBean() {
    }

    public String register() {
        try {
            UserDto userDto = new UserDto();
            userDto.setEmail(getEmail());
            userDto.setUsername(getUserName());
            userDto.setPassword(hashingUtil.getHashedPassword(getPassword()));
            userDto.setImageUrl(imageUrl);
            userDto.setPoints(100);
            GenderDto genderDto = new GenderDto();
            genderDto.setGender(getGender());
            userDto.setGender(genderDto);
            System.out.println(userDto);
            userService.RegisterUser(userDto);
            return "";
        } catch (ServiceException ex) {
            Logger.getLogger(RegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void save() {

        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File(System.getProperty("user.home")+"\\shareit\\images\\userProfile\\", Paths.get(file.getSubmittedFileName()).getFileName().toString()).toPath());
            imageUrl = System.getProperty("user.home")+"\\shareit\\images\\userProfile\\" + Paths.get(file.getSubmittedFileName()).getFileName().toString();
        } catch (IOException e) {
            // Show faces message
            FacesMessage facesMessage = new FacesMessage("error uploading image");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, facesMessage);
        }
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
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
