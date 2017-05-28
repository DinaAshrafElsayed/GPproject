/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.AddressEntity;
import javax.ejb.Stateless;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "AddressDaoImpl")
public class AddressDaoImpl extends GenericDaoImpl<AddressEntity> implements AddressDao{
    
    public AddressDaoImpl() {
        super(AddressEntity.class);
    }
    
}
