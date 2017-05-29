/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.dao.GenericDao;
import eg.iti.shareit.model.entity.ItemEntity;
import java.util.List;

/**
 *
 * @author Yousef
 */
public interface ItemDao extends GenericDao<ItemEntity>{
    public List<ItemEntity> searchItem(String name, int category) throws DatabaseRollbackException;
    
    public boolean isItemAvailable(int itemId) throws DatabaseRollbackException;
    
    public boolean isPendeingRequest(int itemId) throws DatabaseRollbackException;
}
