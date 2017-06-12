/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.model.util.ImageUtil;
import eg.iti.shareit.service.CategoryService;
import eg.iti.shareit.service.ItemService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author El-Greatly
 */
@Named(value = "itemEditBean")
@RequestScoped
public class ItemEditBean implements Serializable{
    
    private ItemDto item;
     private CategoryDto category;
    private String description;
    private String imageUrl;
    private int isAvailabe;
    private String name;
    private int points;
    private String tags;
    private Part file;
    private int id;
    
    @EJB
    private ItemService itemService;
    
    @EJB
    private CategoryService categoryService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getIsAvailabe() {
        return isAvailabe;
    }

    public void setIsAvailabe(int isAvailabe) {
        this.isAvailabe = isAvailabe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
     @PostConstruct
    public void init() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            System.out.println("----------- in id "+request.getParameter("id"));
            id = Integer.parseInt(request.getParameter("id"));
            
            item = itemService.getItemById(id);
            name = item.getName();
            tags = item.getTags();
            description = item.getDescription();
            category = item.getCategory();
            isAvailabe = item.getIsAvailable();
            points = item.getPoints();
            imageUrl = item.getImageUrl();
        } catch (ServiceException ex) {
            Logger.getLogger(ItemEditBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void save(){
    System.out.println("in save method");
        imageUrl = ImageUtil.SaveImage(file, System.getProperty("user.home") + "\\shareit\\images\\sharedItems\\");
    }
    public void updateItem(){
        try {
            System.out.println("-------------- in update item method");
            String categoryName = item.getCategory().getName();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto = categoryService.getCategoryByName(categoryName);
            item.setCategory(categoryDto);
            item.setDescription(description);
            item.setImageUrl(imageUrl);
            item.setIsAvailable(isAvailabe);
            item.setName(name);
            item.setPoints(points);
            item.setTags(tags);
            itemService.updateSharedItem(item);
        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
