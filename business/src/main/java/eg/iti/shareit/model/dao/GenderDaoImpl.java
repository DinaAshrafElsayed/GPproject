/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.GenderEntity;
import javax.ejb.Stateless;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "GenderDaoImpl")
public class GenderDaoImpl extends GenericDaoImpl<GenderEntity> implements GenderDao{
    
    public GenderDaoImpl() {
        super(GenderEntity.class);
    }
    
}
