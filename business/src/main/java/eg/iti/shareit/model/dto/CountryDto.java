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
public class CountryDto implements java.io.Serializable, GenericDto{
    private int id;
    private String country;

    public CountryDto() {
    }

    public CountryDto(int id, String country) {
        this.id = id;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
