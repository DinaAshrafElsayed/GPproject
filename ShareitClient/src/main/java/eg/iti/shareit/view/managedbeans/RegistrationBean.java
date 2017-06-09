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
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Dina Ashraf
 */
@ManagedBean(name = "registerBean", eager = true)
@SessionScoped
public class RegistrationBean implements Serializable {

    @EJB
    private HashingUtil hashingUtil;

    @EJB
    UserService userService;
    @EJB
    AddressService addressService;
    private String gender;
    private String userName;
    private String email;
    private String password;
    private Part file;
    private String imageUrl = "";
    private List<CountryDto> countries;
    private List<StateDto> states;
    private List<CityDto> cities;
    private CountryDto country;
    private CityDto city;
    private StateDto state;
    public RegistrationBean() {
    }

    public String register() {
        try {
            UserDto user = userService.getUserByEmail(email);
            if (user == null) {
                UserDto userDto = new UserDto();
                userDto.setEmail(getEmail());
                userDto.setUsername(getUserName());
                userDto.setPassword(hashingUtil.getHashedPassword(getPassword()));
                userDto.setImageUrl(imageUrl);
                userDto.setPoints(100);
                GenderDto genderDto = new GenderDto();
                genderDto.setGender(getGender());
                userDto.setGender(genderDto);
                AddressDto addressDto = new AddressDto();
                addressDto.setCountry(country);
                addressDto.setState(state);
                addressDto.setCity(city);
                userDto.setAddressDto(addressDto);
                System.out.println(userDto);
                userService.RegisterUser(userDto);
                return "";
            } else {
                System.out.println("in error part ");
                //faces error message email already exists
                FacesMessage facesMessage = new FacesMessage("This email isn't valid");
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage("form:email", facesMessage);
                return null;
            }
        } catch (ServiceException ex) {
            Logger.getLogger(RegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void onCountryChange(BigDecimal countryId)
    {
        System.out.println("in on country Change function");
        try {
            states = addressService.getStates(countryId);
            cities = addressService.getCities(countryId);
            country = addressService.getCountry(countryId);
            System.out.println("countryid is "+countryId);
            System.out.println("country is "+country);
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
        System.out.println("country is ! "+ country);
        try {
            System.out.println("country id :"+country.getId()+" stateId :"+stateId);
            cities = addressService.getCities(country.getId(), stateId);
            System.out.println("cities are "+cities);
        } catch (ServiceException ex) {
            Logger.getLogger(RegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void save() {

        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File(System.getProperty("user.home") + "\\shareit\\images\\userProfile\\", Paths.get(file.getSubmittedFileName()).getFileName().toString()).toPath());
            imageUrl = System.getProperty("user.home") + "\\shareit\\images\\userProfile\\" + Paths.get(file.getSubmittedFileName()).getFileName().toString();
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

    @PostConstruct
    public void init() {
        try {
            setCountries(addressService.getCountries());
            states = new ArrayList<>();
            cities = new ArrayList<>();
            System.out.println("-------------------- addresses " + getCountries());
        } catch (ServiceException ex) {
            Logger.getLogger(ItemManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the countries
     */
    public List<CountryDto> getCountries() {
        return countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(List<CountryDto> countries) {
        this.countries = countries;
    }

    /**
     * @return the states
     */
    public List<StateDto> getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(List<StateDto> states) {
        this.states = states;
    }

    /**
     * @return the cities
     */
    public List<CityDto> getCities() {
        return cities;
    }

    /**
     * @param cities the cities to set
     */
    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }

    /**
     * @return the country
     */
    public CountryDto getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(CountryDto country) {
        this.country = country;
    }

    /**
     * @return the city
     */
    public CityDto getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(CityDto city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public StateDto getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(StateDto state) {
        this.state = state;
    }
}