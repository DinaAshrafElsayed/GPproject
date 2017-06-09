/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.model.dto.AddressDto;
import eg.iti.shareit.model.entity.ActivityEntity;
import eg.iti.shareit.model.entity.AddressEntity;
import eg.iti.shareit.model.entity.CityEntity;
import eg.iti.shareit.model.entity.CountryEntity;
import eg.iti.shareit.model.entity.ItemEntity;
import eg.iti.shareit.model.entity.StateEntity;
import eg.iti.shareit.model.entity.UserEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

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
    public List<ItemEntity> getRelatedItems(ItemEntity myItem) throws DatabaseRollbackException {
        Query query;
        String[] tags = myItem.getTags().split(",");
        String queryString = "SELECT i FROM ItemEntity i WHERE i.id <> :itemId ";
        queryString += "And ( ";
        for(String tag : tags){
            queryString += "i.tags LIKE '%"+tag+"%' or ";
        }
        queryString = queryString.substring(0,queryString.length()-3);
        queryString += " )";
        query = getEntityManager().createQuery(queryString);
        query.setParameter("itemId", myItem.getId());
        List<ItemEntity> itemsList = query.setMaxResults(3).getResultList();
        
        return itemsList;
    }

    @Override
    public List<ItemEntity> searchItem(String name, int categoryId) throws DatabaseRollbackException {
        Query query;
        String queryString = "Select i From ItemEntity i ";
        boolean flag = false;
        if (name != null) {
            flag = true;
            queryString += " where i.name LIKE :name ";
        }
        if (categoryId != 0) {
            if (flag) {
                queryString += " and ";
            } else {
                queryString += "where ";
            }
            queryString += "i.category.id = :categoryId";
        }

        query = getEntityManager().createQuery(queryString);
        if (name != null) {
            query.setParameter("name", '%' + name + '%');
        }

        if (categoryId != 0) {
            query.setParameter("categoryId", new BigDecimal(categoryId));
        }

        try {
            List<ItemEntity> itemList = query.getResultList();
            if (itemList != null) {
                return itemList;
            } else {
                throw new DatabaseRollbackException("ItemEntities with name <" + name + "> and categoryId <" + categoryId + "> Not Found");
            }
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }

    }

    @Override
    public boolean isItemAvailable(int itemId) throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("Select i From ItemEntity i where i.isAvailable = 1 and i.id = :itemId");
        query.setParameter("itemId", new BigDecimal(itemId));

        try {
            List<ActivityEntity> activityList = query.getResultList();
            if (activityList != null) {
                if (activityList.size() == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new DatabaseRollbackException("no such item");
            }
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public boolean isPendeingRequest(int itemId) throws DatabaseRollbackException {
        Query query = getEntityManager().createQuery("select status from StatusEntity INNER JOIN ActivityEntity ON StatusEntity.id = ActivityEntity.id");
        List activityList = query.getResultList();
        try {
            if (activityList != null) {
                String result = activityList.get(0).toString();
                System.out.println("activity list: " + result);
                if (result.equals("pending")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                throw new DatabaseRollbackException("there is no related item");
            }
        } catch (PersistenceException ex) {
            throw new DatabaseRollbackException(ex.getMessage());
        }
    }

    @Override
    public int addItem(ItemEntity item) throws DatabaseRollbackException {

        int i = 0;
        try {

//            Query query = getEntityManager().createNativeQuery("insert into T_ITEM (NAME,DESCRIPTION,CATEGORY,IS_AVAILABLE,PUBLISH_DATE,POINTS)" + " values (?,?,?,?,?,?)");
//            query.setParameter(1, item.getName());
//            query.setParameter(2, item.getDescription());
//            query.setParameter(3, item.getCategory());
//            query.setParameter(4, item.getIsAvailable());
//            query.setParameter(5, item.getPublishDate());
//            query.setParameter(6, item.getPoints());
//             i = query.executeUpdate();
//            System.out.println("done in the database");
            getEntityManager().persist(item);
        } catch (Exception ex) {
            throw new RollbackException("cannot presist the item object using  presist");
        }
        return i;

    }

    @Override
    public List<ItemEntity> searchItem(String name) throws DatabaseRollbackException {
        return searchItem(name, 0);
    }

    @Override
    public List<ItemEntity> searchItem(int category) throws DatabaseRollbackException {
        return searchItem(null, category);
    }

    @Override
    public List<ItemEntity> searchItem(CountryEntity countryEntity, StateEntity stateEntity, CityEntity cityEntity) throws DatabaseRollbackException {
        //Query query = getEntityManager().createQuery("From ItemEntity i where i.");
        Query query = getEntityManager().createQuery("Select i From ItemEntity i where "
                + "i.userFrom.address.country = :country and "
                + "i.userFrom.address.state = :state and "
                + "i.userFrom.address.city = :city");
        
        query.setParameter("country", countryEntity)
                .setParameter("state", stateEntity)
                .setParameter("city", cityEntity);
        try {
            List<ItemEntity> items = query.getResultList();
            if (items != null) {
                return items;
            }
        } catch (PersistenceException e) {
            throw new DatabaseRollbackException(e.getMessage());
        }
        return null;

    }

}
