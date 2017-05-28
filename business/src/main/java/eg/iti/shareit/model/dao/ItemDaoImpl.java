/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.entity.ItemEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "ItemDaoImpl")
public class ItemDaoImpl extends GenericDaoImpl<ItemEntity> implements ItemDao {
    
    public ItemDaoImpl() {
        super(ItemEntity.class);
    }

    
    @Override
    public List<ItemEntity> searchItem(String name, int categoryId) throws DatabaseRollbackException {
        Query query;
        String queryString = "Select i From ItemEntity i ";
        boolean flag = false;
        if(name != null){
            flag = true;
            queryString += " where i.name = :name ";
        }
        if(categoryId != 0){
            if(flag)
                queryString += " and ";
            else
                queryString += "where ";
            queryString += "i.category.id = :categoryId";
        }
        
        query = getEntityManager().createQuery(queryString);
        if(name != null)
            query.setParameter("name", name);
        
        if(categoryId != 0)
            query.setParameter("categoryId", new BigDecimal(categoryId));

        try {
            List<ItemEntity> itemList = query.getResultList();
            if (itemList != null ) {
                return itemList;
            } else {
                throw new DatabaseRollbackException("ItemEntities with name <" + name + "> and categoryId <"+categoryId+"> Not Found");
            }
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    

    }

    @Override
    public boolean isItemAvailable(int itemId) throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("Select i From ItemEntity i where i.isAvailable = 1 and i.id = :itemId");
        query.setParameter("itemId", new BigDecimal(itemId));
        
        try{
            List<ActivityEntity> activityList = query.getResultList();
            if(activityList != null ){
                if(activityList.size() == 1)
                    return true;
                else
                    return false;
            }else{ 
               throw new DatabaseRollbackException("no such item");
            }
        }catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    
    
}
