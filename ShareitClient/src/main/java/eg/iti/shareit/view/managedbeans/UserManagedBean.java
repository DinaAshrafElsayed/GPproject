/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.CityDto;
import eg.iti.shareit.model.dto.CountryDto;
import eg.iti.shareit.model.dto.StateDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.util.HashingUtil;
import eg.iti.shareit.service.AddressService;
import eg.iti.shareit.service.UserService;
import java.io.File;
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
    private String confirmPassword;
    private List<CountryDto> countries;
    private List<StateDto> states;
    private List<CityDto> cities;
      private Part file;

    @EJB
    UserService userService;

    public List<CountryDto> getCountries() {
        return countries;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }

    public List<StateDto> getStates() {
        return states;
    }

    public void setStates(List<StateDto> states) {
        this.states = states;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }
    @EJB
    private HashingUtil hashingUtil;

    @EJB
    AddressService addressService;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserManagedBean() {
        System.out.println("------------- in const user");
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDto getUserDto() {
        return SessionUtil.getUser();
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @PostConstruct
    public void init() {
        try {
            System.out.println("----------------------- in post construct user ");
            userDto = this.getUserDto();
            setCountries(addressService.getCountries());
       
            System.out.println("------------ yrb l country tzbot "+countries.size());
            states = new ArrayList<>();
            cities = new ArrayList<>();
            System.out.println("-------------------- addresses " + getCountries());
            
            System.out.println("++++++++++++++++++++++++ at post construct " + userDto.getUsername());
        } catch (ServiceException ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void onCountryChange(BigDecimal countryId)
    {
        System.out.println("in on country Change function");
        try {
            states = addressService.getStates(countryId);
            cities = addressService.getCities(countryId);
            userDto.getAddressDto().setCountry(addressService.getCountry(countryId));
            System.out.println("countryid is "+countryId);
    //        System.out.println("country is "+country);
            System.out.println("states are "+ states);
            System.out.println("cities are "+cities);
        } catch (ServiceException ex) {
            Logger.getLogger(RegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    public void onStateChange(BigDecimal stateId)
    {
        System.out.println("in function on stateChange");
        System.out.println("state id is "+stateId);
        System.out.println("country is ! "+ userDto.getAddressDto().getCountry());
        try {
            System.out.println("country id :"+userDto.getAddressDto().getCountry().getId()+" stateId :"+stateId);
            cities = addressService.getCities(userDto.getAddressDto().getCountry().getId(), stateId);
            System.out.println("cities are "+cities);
        } catch (ServiceException ex) {
            Logger.getLogger(RegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void save() {

        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File(System.getProperty("user.home") + "\\shareit\\images\\userProfile\\", Paths.get(file.getSubmittedFileName()).getFileName().toString()).toPath());
            userDto .setImageUrl( System.getProperty("user.home") + "\\shareit\\images\\userProfile\\" + Paths.get(file.getSubmittedFileName()).getFileName().toString());;
        } catch (IOException e) {
            // Show faces message
            FacesMessage facesMessage = new FacesMessage("error uploading image");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, facesMessage);
        }
    }
    public String editUser(){
        System.out.println("------------------------- in edit");
        
        return "";
    }

}
