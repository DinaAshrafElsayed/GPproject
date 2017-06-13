/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.AddressDto;
import eg.iti.shareit.model.dto.CityDto;
import eg.iti.shareit.model.dto.CountryDto;
import eg.iti.shareit.model.dto.GenderDto;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.model.dto.StateDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.util.HashingUtil;
import eg.iti.shareit.model.util.ImageUtil;
import eg.iti.shareit.service.AddressService;
import eg.iti.shareit.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author sara metwalli
 */
@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    private UserDto userDto;
    private UserDto user2;
    private String username;
    private String password;

    private String email;
    private String gender;
    private String image_url;
    private String confirmPassword;
    private CountryDto country;
    private CityDto city;
    private StateDto state;
    private Part file;
    private boolean canEdit = false;
    private List<ItemDto> items;

    @EJB
    UserService userService;

    @EJB
    private HashingUtil hashingUtil;

    @EJB
    AddressService addressService;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public StateDto getState() {
        return state;
    }

    public void setState(StateDto state) {
        this.state = state;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public HashingUtil getHashingUtil() {
        return hashingUtil;
    }

    public void setHashingUtil(HashingUtil hashingUtil) {
        this.hashingUtil = hashingUtil;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostConstruct
    public void init() {

        userDto = SessionUtil.getUser();
        setUser2(userDto);
        if (getUser2().getEmail().equals(userDto.getEmail())) {
            canEdit = true;
        }
        username = userDto.getUsername();
        email = userDto.getEmail();
        gender = userDto.getGender().getGender();
        image_url = userDto.getImageUrl();
        country = userDto.getAddress().getCountry();
        city = userDto.getAddress().getCity();
        state = userDto.getAddress().getState();
        items = userDto.getItems();
        System.out.println("the user items size is " + items.size());
        
    }

    public void save() {

//        try (InputStream input = file.getInputStream()) {
//            Files.copy(input, new File(System.getProperty("user.home") + "\\shareit\\images\\userProfile\\", Paths.get(file.getSubmittedFileName()).getFileName().toString()).toPath());
//            userDto.setImageUrl(System.getProperty("user.home") + "\\shareit\\images\\userProfile\\" + Paths.get(file.getSubmittedFileName()).getFileName().toString());;
//        } catch (IOException e) {
//            // Show faces message
//            FacesMessage facesMessage = new FacesMessage("error uploading image");
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            facesContext.addMessage(null, facesMessage);
//        }
        System.out.println("in save method");
        image_url = ImageUtil.SaveImage(file, System.getProperty("user.home") + "\\shareit\\images\\userProfile\\");

    }

    public String editUser() {
        System.out.println("------------------------- in edit");
        if (password.equals(confirmPassword)) {
            try {
                userDto.setUsername(username);
                userDto.setEmail(email);
                GenderDto genderDto = new GenderDto();
                genderDto.setGender(gender);
                userDto.setGender(genderDto);
                AddressDto addressDto = new AddressDto();
                addressDto.setCountry(country);
                addressDto.setState(state);
                addressDto.setCity(city);
                userDto.setAddress(addressDto);
                userDto.setPassword(hashingUtil.getHashedPassword(password));
                userDto.setImageUrl(image_url);
                userService.updateUser(userDto);
                SessionUtil.getSession().setAttribute("userDto", userDto);
            } catch (ServiceException ex) {
                Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);

            }
            
        }
        return "Profile?faces-redirect=true";
    }

    public InputStream getImage(String filename) throws FileNotFoundException {
        return new FileInputStream(new File(filename));
    }

    public String goToItem(int id) {
        return "itemDetails.xhtml?id=" + id;
    }

    /**
     * @return the items
     */
    public List<ItemDto> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
    
    public boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    /**
     * @return the user2
     */
    public UserDto getUser2() {
        return user2;
    }

    /**
     * @param user2 the user2 to set
     */
    public void setUser2(UserDto user2) {
        this.user2 = user2;
    }
}
