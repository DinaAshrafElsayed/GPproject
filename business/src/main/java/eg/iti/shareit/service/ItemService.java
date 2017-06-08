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
import eg.iti.shareit.model.dao.CategoryDao;
import eg.iti.shareit.model.dao.ItemDao;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.model.entity.CategoryEntity;
import eg.iti.shareit.model.entity.ItemEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.math.BigDecimal;
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

    @EJB
    private CategoryDao categoryDao;

    public List<CategoryDto> getAllCategories() throws ServiceException {
        try {
            List<CategoryEntity> entities = categoryDao.getAll();
            return mappingUtil.getDtoList(entities, CategoryDto.class);
        } catch (DatabaseRollbackException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<ItemDto> getAllItems() throws ServiceException {
        try {
            List<ItemEntity> itemEntities = itemDao.getAll();
            return mappingUtil.getDtoList(itemEntities, ItemDto.class);
        } catch (DatabaseRollbackException ex) {
            throw new ServiceException(ex.getMessage());
        }

    }

    public List<ItemDto> searchItems(String name, int categoryId) throws ServiceException {
        try {
            List<ItemEntity> itemEntities = itemDao.searchItem(name, categoryId);
            return mappingUtil.getDtoList(itemEntities, ItemDto.class);
        } catch (DatabaseException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean isItemAvailable(int itemId) throws ServiceException {
        try {
            boolean isAvailable = itemDao.isItemAvailable(itemId);
            return isAvailable;
        } catch (DatabaseRollbackException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean addItemForShare(ItemDto item) throws ServiceException {
        boolean flag = false;
        try {
            System.out.println("---------------- in add item for share");

            int added = itemDao.addItem(mappingUtil.getEntity(item, ItemEntity.class));
            if (added != 0) {
                flag = true;
            }
        } catch (DatabaseRollbackException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage());
        }
        return flag;
    }
    
    public ItemDto getItemById(int id) throws ServiceException{
        try {
            ItemEntity itemEntity = itemDao.get(new BigDecimal(id));
            return mappingUtil.getDto(itemEntity, ItemDto.class);
        } catch (DatabaseRollbackException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException(ex.getMessage());
        }
    }
    public ItemDto getItem(BigDecimal id) throws ServiceException {
        try {
            ItemEntity itemEntity = itemDao.get(id);
            return mappingUtil.getDto(itemEntity, ItemDto.class);
        } catch (DatabaseRollbackException e) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, e);
            throw new ServiceException(e.getMessage());
        }
    }
}
