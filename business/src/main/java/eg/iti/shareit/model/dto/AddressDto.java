/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;

/**
 *
 * @author Yousef
 */
public class AddressDto implements java.io.Serializable, GenericDto{
    private int id;
    private int rUser;
    private CountryDto country;
    private CityDto city;
    private StateDto state;

    public AddressDto() {
    }

    public AddressDto(int id, int rUser, CountryDto country, CityDto city, StateDto state) {
        this.id = id;
        this.rUser = rUser;
        this.country = country;
        this.city = city;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getrUser() {
        return rUser;
    }

    public void setrUser(int rUser) {
        this.rUser = rUser;
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
    
    
}
