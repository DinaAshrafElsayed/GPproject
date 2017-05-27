/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.ItemEntity;
import javax.ejb.Stateless;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "ItemDaoImpl")
public class ItemDaoImpl extends GenericDaoImpl<ItemEntity> implements ItemDao {
    
    public ItemDaoImpl() {
        super(ItemEntity.class);
    }
    
}
