/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.model.entity.CategoryEntity;
import eg.iti.shareit.model.entity.ItemEntity;
import eg.iti.shareit.model.util.MappingUtil;
import eg.iti.shareit.service.CategoryService;
import eg.iti.shareit.service.ItemService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author sara metwalli
 */
@ManagedBean(name = "itemManagedBean", eager = true)
@SessionScoped
public class ItemManagedBean implements java.io.Serializable {

    @EJB
    CategoryService categoryService;
    @EJB
    ItemService itemService;
    @EJB(beanName = "MappingUtil")
    private MappingUtil mappingUtil;
    private String name;
    private String description;
    private Date publish_date;
    private int points;
    private String image_url = "";
    private Part file;
    private CategoryDto category;
    private List<CategoryDto> categories;
    private String tags;

    public ItemManagedBean(CategoryService categoryService, String name, String description, Date publish_date, int points, String image_url) {
        this.categoryService = categoryService;
        this.name = name;
        this.description = description;
        this.publish_date = publish_date;
        this.points = points;
        this.image_url = image_url;
    }

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

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ItemManagedBean() {
    }

    @PostConstruct
    public void init() {
        try {
            categories = categoryService.getAllCategories();
            System.out.println("-------------------- categories " + categories);
        } catch (ServiceException ex) {
            Logger.getLogger(ItemManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String test() {

        System.out.println("Test Method ------------------------------======");
        return "register";

    }

    public String addItem() throws ServiceException {
        System.out.println("ru7t le add");

        System.out.println("-------------- in add item");

        ItemDto item = new ItemDto(name, description, (short) 1, publish_date, points, image_url, tags, SessionUtil.getUser());

        //  CategoryEntity catEntity = categoryService.getCategoryEntityFromCategoryDto(category);
        //System.out.println("----------------"+catEntity.getName());
        item.setImageUrl(image_url);
        item.setCategory(category);

        itemService.addItemForShare(item);
        return "";

    }

    public void save() {
        System.out.println("In save method");
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File(System.getProperty("user.home") + "\\shareit\\images\\sharedItems\\"
                    + "\\", Paths.get(file.getSubmittedFileName()).getFileName().toString()).toPath(), REPLACE_EXISTING);
            image_url = System.getProperty("user.home") + "\\shareit\\images\\sharedItems\\" + Paths.get(file.getSubmittedFileName()).getFileName().toString();
            System.out.println("Image url: " + image_url);
        } catch (IOException e) {
            // Show faces message
            FacesMessage facesMessage = new FacesMessage("error uploading image");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, facesMessage);
        }
    }
}
