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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author El-Greatly
 */
@ManagedBean(name = "itemEditBean")
@ViewScoped
public class ItemEditBean implements Serializable {

    private ItemDto item;
    
    private Part file;
    private List<CategoryDto> categories = new ArrayList<>();

    @EJB
    private ItemService itemService;

    @EJB
    private CategoryService categoryService;

    public ItemEditBean() {
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
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
            int id = Integer.parseInt(request.getParameter("id"));

            item = itemService.getItemById(id);
            categories = categoryService.getAllCategories();
            
        } catch (ServiceException ex) {
            Logger.getLogger(ItemEditBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() {
        System.out.println("in save method");
        String image_url = ImageUtil.SaveImage(file, System.getProperty("user.home") + "\\shareit\\images\\sharedItems\\");
        item.setImageUrl(image_url);
    }

    public void updateItem() {
        try {
            System.out.println("-------------- in update item method");
            
            itemService.updateSharedItem(item);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Item is updated Successfully"));
        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InputStream getImage(String filename) throws FileNotFoundException {
        return new FileInputStream(new File(filename));
    }
   
}
