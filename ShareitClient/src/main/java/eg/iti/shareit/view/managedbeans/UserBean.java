/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.UserService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    private String email;
    private String password;
    private UserDto userDto;
    public UserBean() {
    }

    public String login() {
        try {
            userDto = userService.findUser(email, password);
            System.out.println("user dto " + getUserDto());
            if(userDto!= null)
            {
                //save in session
                HttpSession session = SessionUtil.getSession();
                session.setAttribute("userDto", userDto);
                System.out.println("user saved in session");
            }
            else
            {
                //return faces error msg
                //error email or password
            }
            
        } catch (ServiceException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //supposedly return to home page
        return "";
    }
    public String logout()
    {
        userDto = null;
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        System.out.println("session invalidated");
        return "register";
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

    /**
     * @return the userDto
     */
    public UserDto getUserDto() {
        return userDto;
    }

    /**
     * @param userDto the userDto to set
     */
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
