/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.service.ItemService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Yousef
 */
@ManagedBean(name = "itemBean")
@SessionScoped
public class ItemBean implements Serializable{

    @EJB
    private ItemService itemService;
    private List<ItemDto> items;
    private List<CategoryDto> categories;
    private String searchString;
    private int categoryId;
    
    public ItemBean() {
    }
    
    @PostConstruct
    public void init() {
        try {
            if (itemService.getAllItems()!= null) {
                items = itemService.getAllItems();
            }
            categories = itemService.getAllCategories();
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
    
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
    
    
    public void doSearch(){
        try{
            List<ItemDto> items = itemService.searchItems(searchString, categoryId);
            System.out.println("items "+items.size());
           this.items = items;
        }catch(ServiceException ex){
             Logger.getLogger(SearchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
