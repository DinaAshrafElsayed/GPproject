/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.ItemDao;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.model.entity.ActivityEntity;
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
    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;

    public List<ItemDto> getAllItems() throws ServiceException {
        List<ItemEntity> itemEntities = itemDao.getAll();
        return mappingUtil.getDtoList(itemEntities, ItemDto.class);
    }

    
}
