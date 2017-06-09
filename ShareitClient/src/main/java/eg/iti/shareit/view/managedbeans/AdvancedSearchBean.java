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
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.model.dto.StateDto;
import eg.iti.shareit.service.AddressService;
import eg.iti.shareit.service.ItemService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Yousef
 */
@ManagedBean(name = "AdvancedSearch", eager = true)
@SessionScoped
public class AdvancedSearchBean implements Serializable {

    /**
     * Creates a new instance of SearchBean
     */
    @EJB
    private ItemService itemService;
    @EJB
    AddressService addressService;

    @Inject
    ItemBean itemBean;
   
    private List<CountryDto> countries;
    private List<StateDto> states;
    private List<CityDto> cities;
    private CountryDto country;
    private CityDto city;
    private StateDto state;
    
   
    public void onCountryChange(BigDecimal countryId) {
        System.out.println("in on country Change function");
        try {
            setStates(addressService.getStates(countryId));
            setCities(addressService.getCities(countryId));
            setCountry(addressService.getCountry(countryId));
            System.out.println("countryid is " + countryId);
            System.out.println("country is " + getCountry());
            System.out.println("states are " + getStates());
            System.out.println("cities are " + getCities());
        } catch (ServiceException ex) {
            Logger.getLogger(RegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onStateChange(BigDecimal stateId) {
        System.out.println("in function on stateChange");
        System.out.println("state id is " + stateId);
        System.out.println("country is ! " + getCountry());
        try {
            System.out.println("country id :" + getCountry().getId() + " stateId :" + stateId);
            setCities(addressService.getCities(getCountry().getId(), stateId));
            System.out.println("cities are " + getCities());
        } catch (ServiceException ex) {
            Logger.getLogger(RegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String advancedSearch() {
        try {
            AddressDto addressDto = new AddressDto();
            addressDto.setCountry(getCountry());
            addressDto.setState(getState());
            addressDto.setCity(getCity());
            System.out.println("addressDto is : "+addressDto);
            List<ItemDto> items = itemService.searchByLocation(addressDto);
            System.out.println("items " + items.size());
            itemBean.setItems(items);
            return "items";
        } catch (ServiceException ex) {
            Logger.getLogger(SearchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @PostConstruct
    public void init() {
        try {
            setCountries(addressService.getCountries());
            cities = new ArrayList<>();
            states = new ArrayList<>();
            System.out.println("-------------------- addresses " + getCountries());
        } catch (ServiceException ex) {
            Logger.getLogger(AdvancedSearchBean.class.getName()).log(Level.SEVERE, null, ex);
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