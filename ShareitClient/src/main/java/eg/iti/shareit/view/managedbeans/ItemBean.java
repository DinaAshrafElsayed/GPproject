/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.service.ItemService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author Yousef
 */
@ManagedBean(name = "itemBean")
@RequestScoped
public class ItemBean {

    @EJB
    private ItemService itemService;
    private List<ItemDto> items;
    
    public ItemBean() {
    }
    
    @PostConstruct
    public void init() {
        try {
            if (itemService.getAllItems()!= null) {
                
                items = itemService.getAllItems();
            }
        } catch (ServiceException ex) {
            Logger.getLogger(Requests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
    
    
}
