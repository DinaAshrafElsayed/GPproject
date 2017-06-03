/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.model.entity.CategoryEntity;
import eg.iti.shareit.model.entity.ItemEntity;
import eg.iti.shareit.service.CategoryService;
import eg.iti.shareit.service.ItemService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sara metwalli
 */
@ManagedBean(name = "itemBean")
@SessionScoped
public class ItemManagedBean {

    @EJB
    CategoryService categoryService;
     @EJB
    ItemService itemService;

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    String name;
    String description;
    Date publish_date;
    BigInteger points;
    String image_url;
   CategoryDto  category;
    List<CategoryDto> categories;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public BigInteger getPoints() {
        return points;
    }

    public void setPoints(BigInteger points) {
        this.points = points;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }



    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public ItemManagedBean() {
    }

    public ItemManagedBean(CategoryService categoryService, String name, String description, Date publish_date, BigInteger points, String image_url) {
        this.categoryService = categoryService;
        this.name = name;
        this.description = description;
        this.publish_date = publish_date;
        this.points = points;
        this.image_url = image_url;
    }

    @PostConstruct
    public void init() {
        try {
            categories = categoryService.getAllCategories();
        } catch (ServiceException ex) {
            Logger.getLogger(ItemManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String addItem() {
        
        try {
            ItemEntity item=new ItemEntity(name, description, (short)1, publish_date, points);
           // item.setImageUrl(image_url);
            CategoryEntity catEntity=categoryService.getCategoryEntityFromCategoryDto(category);
            item.setCategory(catEntity);
            itemService.addItemForShare(item);
            
        } catch (ServiceException ex) {
            Logger.getLogger(ItemManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
}
