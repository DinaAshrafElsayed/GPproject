/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.common.entity.GenericEntity;
import eg.iti.shareit.model.entity.TActivityEntity;
import java.util.List;

/**
 *
 * @author Adel Zaid
 */
public interface ActivityDao extends GenericDao<TActivityEntity> {

    public List<TActivityEntity> getAllActivities() throws DatabaseRollbackException;
}
