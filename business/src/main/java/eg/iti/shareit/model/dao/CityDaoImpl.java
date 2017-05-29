/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.CityEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "CityDaoImpl")
public class CityDaoImpl extends GenericDaoImpl<CityEntity> implements CityDao {

    @PersistenceContext
    EntityManager entityManager;

    public CityDaoImpl() {
        super(CityEntity.class);
    }

    @Override
    public CityEntity getCityByName(String city) {
        CityEntity cityEntity = (CityEntity) entityManager.createNamedQuery("CityEntity.findByCity").
                setParameter("city", city).getSingleResult();
        return cityEntity;
    }

}
