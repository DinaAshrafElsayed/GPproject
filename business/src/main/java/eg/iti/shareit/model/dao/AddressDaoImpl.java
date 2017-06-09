/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.dto.AddressDto;
import eg.iti.shareit.model.dto.CityDto;
import eg.iti.shareit.model.dto.CountryDto;
import eg.iti.shareit.model.dto.StateDto;
import eg.iti.shareit.model.entity.AddressEntity;
import eg.iti.shareit.model.entity.CategoryEntity;
import eg.iti.shareit.model.entity.CityEntity;
import eg.iti.shareit.model.entity.CountryEntity;
import eg.iti.shareit.model.entity.StateEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "AddressDaoImpl")
public class AddressDaoImpl extends GenericDaoImpl<AddressEntity> implements AddressDao {

    @EJB
    CountryDao countryDao;
    @EJB
    StateDao stateDao;
    @EJB
    CityDao cityDao;

    public AddressDaoImpl() {
        super(AddressEntity.class);
    }

    @Override
    public List<StateEntity> getRelatedStates(BigDecimal countryId) throws DatabaseRollbackException {
        try {
            Query query = getEntityManager().createQuery("Select a.state From AddressEntity a where a.country = :country");
            query.setParameter("country", countryDao.get(countryId));
            List<StateEntity> states = query.getResultList();
            if (states != null) {
                System.out.println("In address Dao geting related States " + states.size());
                return states;
            } else {
                System.out.println("-------------------- states null");
                return null;
            }
        } catch (DatabaseRollbackException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public List<CityEntity> getRelatedCities(BigDecimal countryId) throws DatabaseRollbackException {
        try {
            Query query = getEntityManager().createQuery("Select a.city From AddressEntity a where a.country = :country");
            query.setParameter("country", countryDao.get(countryId));
            List<CityEntity> cities = query.getResultList();
            if (cities != null) {
                System.out.println("In address Dao geting related cities " + cities.size());
                return cities;
            } else {
                System.out.println("-------------------- states null");
                return null;
            }
        } catch (DatabaseRollbackException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public List<CityEntity> getRelatedCities(BigDecimal countryId, BigDecimal stateId) throws DatabaseRollbackException {
        try {
            Query query = getEntityManager().createQuery("Select a.city From AddressEntity a where a.state = :state  and a.country = :country");
            query.setParameter("state", stateDao.get(stateId))
                    .setParameter("country", countryDao.get(countryId));
            List<CityEntity> cities = query.getResultList();
            if (cities != null) {
                System.out.println("In address Dao geting related cities " + cities.size());
                return cities;
            } else {
                System.out.println("-------------------- states null");
                return null;
            }
        } catch (DatabaseRollbackException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public AddressEntity getAddress(AddressEntity addressEntity) throws DatabaseRollbackException {
        System.out.println("");
        try {
            StateEntity state = addressEntity.getState();
            CountryEntity country = addressEntity.getCountry();
            CityEntity city = addressEntity.getCity();
            System.out.println("state id is " + state);
            System.out.println("country id is " + country);
            System.out.println("city id is " + city);
            Query query;
            if (state != null) {
                query = getEntityManager().createQuery("From AddressEntity a where a.state = :state  and a.country = :country and a.city = :city");
                query.setParameter("state", state)
                        .setParameter("country", country)
                        .setParameter("city", city);
            } else {
                query = getEntityManager().createQuery("From AddressEntity a where a.country = :country and a.city = :city");
                query.setParameter("country", country)
                        .setParameter("city", city);
            }
            addressEntity = (AddressEntity) query.getSingleResult();
            if (addressEntity != null) {
                System.out.println("In address Dao geting address " + addressEntity);
                return addressEntity;
            } else {
                System.out.println("address is null");
                throw new DatabaseRollbackException("no such address");
            }
        } catch (DatabaseRollbackException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }
}
