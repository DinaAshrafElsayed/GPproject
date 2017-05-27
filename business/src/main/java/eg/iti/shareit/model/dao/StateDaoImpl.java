/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.StateEntity;
import javax.ejb.Stateless;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "StateDaoImpl")
public class StateDaoImpl extends GenericDaoImpl<StateEntity> implements StateDao{
    
    public StateDaoImpl() {
        super(StateEntity.class);
    }
    
}
