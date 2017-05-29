/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.CountryEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "CountryDaoImpl")
public class CountryDaoImpl extends GenericDaoImpl<CountryEntity> implements CountryDao{
   @PersistenceContext
    EntityManager entityManager;
    public CountryDaoImpl() {
        super(CountryEntity.class);
    }

    @Override
    public CountryEntity getCountryByName(String name) {
         CountryEntity countryEntity = (CountryEntity) entityManager.createNamedQuery("CountryEntity.findByCountry").
                setParameter("country", name).getSingleResult();
        return countryEntity;
    }
    
}
