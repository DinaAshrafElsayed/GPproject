/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.CategoryEntity;
import javax.ejb.Stateless;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "CategoryDaoImpl")
public class CategoryDaoImpl extends GenericDaoImpl<CategoryEntity> implements CategoryDao{
    
    public CategoryDaoImpl() {
        super(CategoryEntity.class);
    }
    
}
