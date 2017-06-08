/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;
import java.math.BigDecimal;

/**
 *
 * @author Yousef
 */
public class AddressDto implements java.io.Serializable, GenericDto {

    private BigDecimal id;

    private CountryDto country;
    private CityDto city;
    private StateDto state;

    public AddressDto() {
    }

    public AddressDto(BigDecimal id, CountryDto country, CityDto city, StateDto state) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.state = state;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AddressDto{" + "id=" + id + ", country=" + country + ", city=" + city + ", state=" + state + '}';
    }

}
