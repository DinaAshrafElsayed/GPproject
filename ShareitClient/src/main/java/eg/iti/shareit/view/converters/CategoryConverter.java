/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.converters;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.service.CategoryService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sara metwalli
 */
@FacesConverter("catConverter")

public class CategoryConverter  implements Converter{
    
    @EJB
    CategoryService categoryService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return categoryService.getCategoryByName(value);
        } catch (ServiceException ex) {
            Logger.getLogger(CategoryConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
      
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       CategoryDto dto =(CategoryDto)value;
      return  dto.getName();
    }
    
}
