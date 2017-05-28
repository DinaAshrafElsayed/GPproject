/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.DatabaseRollbackException;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.ActivityDao;
import eg.iti.shareit.model.dao.ItemDao;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.model.entity.ItemEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
public class ItemService {

    private static final Logger logger = Logger.getLogger(ItemService.class.getName());

    @EJB
    private ItemDao itemDao;
    @EJB
    private ActivityDao activityDao;
    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    public List<ItemDto> getAllItems() throws ServiceException {
        List<ItemEntity> itemEntities = itemDao.getAll();
        return mappingUtil.getDtoList(itemEntities, ItemDto.class);
    }

    public List<ItemDto> searchItems(String name,int categoryId) throws ServiceException{
        try {
            List<ItemEntity> itemEntities = itemDao.searchItem(name,categoryId);
            return mappingUtil.getDtoList(itemEntities,ItemDto.class);
        } catch (DatabaseException ex) {
            logger.log(Level.SEVERE,ex.getMessage(),ex);

            throw new ServiceException(ex.getMessage());
        }
    }
    
    public boolean isItemAvailable(int itemId) throws ServiceException{
        try{
            boolean isAvailable = itemDao.isItemAvailable(itemId);
            return isAvailable;
        } catch (DatabaseRollbackException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage());
        }
    }
    
}
