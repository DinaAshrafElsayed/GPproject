/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.dto.CountryDto;
import eg.iti.shareit.model.entity.CountryEntity;
import javax.ejb.Stateless;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "CountryDaoImpl")
public class CountryDaoImpl extends GenericDaoImpl<CountryEntity> implements CountryDao{
    
    public CountryDaoImpl() {
        super(CountryEntity.class);
    }
    
}
