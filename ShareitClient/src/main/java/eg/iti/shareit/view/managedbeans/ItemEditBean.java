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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author El-Greatly
 */
@ManagedBean(name = "itemEditBean",eager = true)
@RequestScoped
public class ItemEditBean implements Serializable {

    private ItemDto item;
    private CategoryDto category;
    private String description;
    private String image_url;
    private int isAvailabe;
    private String name;
    private int points;
    private String tags;
    private Part file;
    private int id;
    private List<CategoryDto> categories = new ArrayList<>();
    private int categoryId;

    @EJB
    private ItemService itemService;

    @EJB
    private CategoryService categoryService;

    @Inject
    private ListItemsBean listItems;

    public ItemEditBean() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ListItemsBean getListItems() {
        return listItems;
    }

    public void setListItems(ListItemsBean listItems) {
        this.listItems = listItems;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

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
            System.out.println("----------- in init method " + request.getParameter("id"));
            id = Integer.parseInt(request.getParameter("id"));

            item = itemService.getItemById(id);
            name = item.getName();
            tags = item.getTags();
            description = item.getDescription();
            category = item.getCategory();
            isAvailabe = item.getIsAvailable();
            points = item.getPoints();
            image_url = item.getImageUrl();
            categories = categoryService.getAllCategories();
        } catch (ServiceException ex) {
            Logger.getLogger(ItemEditBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() {
        System.out.println("in save method");
        image_url = ImageUtil.SaveImage(file, System.getProperty("user.home") + "\\shareit\\images\\sharedItems\\");
    }

    public void updateItem() {
        try {
            System.out.println("-------------- in update item method");
            String categoryName = item.getCategory().getName();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto = categoryService.getCategoryByName(categoryName);
            item.setCategory(categoryDto);
            item.setDescription(description);
            item.setImageUrl(image_url);
            item.setIsAvailable(isAvailabe);
            item.setName(name);
            item.setPoints(points);
            item.setTags(tags);
            itemService.updateSharedItem(item);
        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InputStream getImage(String filename) throws FileNotFoundException {
        return new FileInputStream(new File(filename));
    }
}
