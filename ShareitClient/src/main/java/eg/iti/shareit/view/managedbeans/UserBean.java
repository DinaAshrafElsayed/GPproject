/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.BorrowStateDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.ItemTrackingService;
import eg.iti.shareit.service.NotificationService;
import eg.iti.shareit.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dina Ashraf
 */
@ManagedBean(name = "user", eager = true)
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    UserService userService;
    @EJB
    ItemTrackingService itemTrackingService;
    @EJB
    NotificationService notificationService;

    private String email;
    private String password;
    private int notificationNumber;
    public static int currentItemId;
    private int ItemNum;

    public UserBean() {
    }

    public String login() {
        try {
            UserDto userDto = userService.findUser(email, password);
            System.out.println("user dto " + userDto);
            //userDto = userService.findUser(email, password);
            System.out.println("user dto ---------------------- " + getUserDto());
            if (userDto != null) {
                //save in session
                HttpSession session = SessionUtil.getSession();
                session.setAttribute("userDto", userDto);
                //Check if the due date is today or after the day
                itemTrackingService.handleBorrowingDueDate(userDto);
                getNotificationNumberFromDB();
                getItemStatusNum();
                System.out.println("user saved in session");
                //supposedly return to home page
                if (SessionUtil.getRequest().getRequestURI().contains("register.xhtml")) {
                    return "items.xhtml?faces-redirect=true";
                } else {
                    return "?faces-redirect=true";
                }
            } else {
                System.out.println("in error part ");
                //faces error message email already exists
                FacesMessage facesMessage = new FacesMessage("Wrong email or passwod");
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage("loginForm:loginEmail", facesMessage);
                return "";
            }

        } catch (ServiceException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public void getNotificationNumberFromDB() {
        try {
            //Get the notifications of the user
            notificationNumber = 0;
            notificationNumber = notificationService.getNotSeenNotifications(SessionUtil.getUser()).size();
        } catch (ServiceException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getItemStatusNum() {
        try {

            ItemNum = itemTrackingService.getBorrowStatus(SessionUtil.getUser()).size();
        } catch (ServiceException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String logout() {
        //userDto = null;
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        System.out.println("session invalidated");
        return "items?faces-redirect=true";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getNotificationNumber() {
        return notificationNumber;
    }

    /**
     * @param NotificationNumbers the NotificationNumbers to set
     */
    public void setNotificationNumber(int notificationNumber) {
        this.notificationNumber = notificationNumber;
    }

    /**
     * @return the userDto
     */
    public UserDto getUserDto() {
        return SessionUtil.getUser();
    }

//    /**
//     * @param userDto the userDto to set
//     */
//    public void setUserDto(UserDto userDto) {
//        this.userDto = userDto;
//    }
    public InputStream getImage(String filename) throws FileNotFoundException {
        return new FileInputStream(new File(filename));
    }

    public int getCurrentItemId() {
        return currentItemId;
    }

    public void setCurrentItemId(int currentItemId) {
        UserBean.currentItemId = currentItemId;
    }

    public int getItemNum() {
        return ItemNum;
    }

    public void setItemNum(int ItemNum) {
        this.ItemNum = ItemNum;
    }

    ////////////////// by sara ///////////////////
       public String goToProfile(BigDecimal id) {
        System.out.println("    ----------- ___ in go to profile " + id);
        if (id != null) {
           
            return "Profile.xhtml?id=" + id;
        } else {
            return "";
        }
    }
   
}
