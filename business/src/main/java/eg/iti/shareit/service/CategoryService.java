/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.service;

import eg.iti.shareit.common.Exception.DatabaseException;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dao.CategoryDao;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.model.entity.CategoryEntity;
import eg.iti.shareit.model.entity.UserEntity;
import eg.iti.shareit.model.util.MappingUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sara metwalli
 */
@Stateless
public class CategoryService {
      private static final Logger logger =  Logger.getLogger(CategoryService.class.getName());

    @EJB
    private CategoryDao categoryDao;

    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;
    
     public CategoryEntity getCategoryByName(String name) throws ServiceException {
        try {
            CategoryEntity categoryEntity = categoryDao.getCategoryByName(name);
          
            return categoryEntity;
        }catch (DatabaseException e) {

            logger.log(Level.SEVERE,e.getMessage(),e);

            throw new ServiceException(e.getMessage());
        }
    }
    
}
