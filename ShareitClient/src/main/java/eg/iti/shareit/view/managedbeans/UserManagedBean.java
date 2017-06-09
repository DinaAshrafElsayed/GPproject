/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author sara metwalli
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    UserService userService;
    private UserDto userDto;

    public UserManagedBean() {
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDto getUserDto() {
        return SessionUtil.getUser();
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @PostConstruct
    public void init() {
        userDto = this.getUserDto();
    }

}
