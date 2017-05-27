/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dao;

import eg.iti.shareit.model.entity.ActivityEntity;
import javax.ejb.Stateless;

/**
 *
 * @author Yousef
 */
@Stateless(mappedName = "ActivityDaoImpl")
public class ActivityDaoImpl extends GenericDaoImpl<ActivityEntity> implements ActivityDao{
    
    public ActivityDaoImpl() {
        super(ActivityEntity.class);
    }
    
}
