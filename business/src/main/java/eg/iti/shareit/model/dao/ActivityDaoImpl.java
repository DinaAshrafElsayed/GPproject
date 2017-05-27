/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.TActivityEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Adel Zaid
 */
@Stateless(mappedName = "ActivityDaoImpl")
public class ActivityDaoImpl extends GenericDaoImpl<TActivityEntity> implements ActivityDao {

    public ActivityDaoImpl() {
        super(TActivityEntity.class);
    }

}
