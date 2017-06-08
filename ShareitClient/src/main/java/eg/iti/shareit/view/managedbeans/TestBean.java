/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Yousef
 */
@Named(value = "testBean")
@SessionScoped
public class TestBean implements Serializable {

    /**
     * Creates a new instance of TestBean
     */
    
    private Date dateField = new Date();
    private Date dateField2;

    public Date getDateField2() {
        return dateField2;
    }

    public void setDateField2(Date dateField2) {
        this.dateField2 = dateField2;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        int i = 3*3;
        this.dateField = dateField;
    }
    public TestBean() {
    }
    
}
