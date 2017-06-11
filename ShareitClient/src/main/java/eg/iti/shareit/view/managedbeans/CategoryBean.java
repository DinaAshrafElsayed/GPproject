/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.service.CategoryService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author El-Greatly
 */
@ManagedBean(name = "category")
@RequestScoped
public class CategoryBean implements Serializable {

    @EJB
    private CategoryService categoryService;
    
    private List<CategoryDto> categories;

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

    @PostConstruct
    public void init() {
        try {
            System.out.println("in get categories intialization ");
            categories = categoryService.getAllCategories();
            System.out.println("categories are " + categories);
        } catch (ServiceException ex) {
            Logger.getLogger(CategoryBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public CategoryBean() {
//        try {
//            System.out.println("in get categories intialization ");
//            categories = categoryService.getAllCategories();
//            System.out.println("categories are " + categories);
//        } catch (ServiceException ex) {
//            Logger.getLogger(CategoryBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
