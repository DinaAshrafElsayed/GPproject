/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.NotificationDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.NotificationService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.inject.Named;

/**
 *
 * @author Adel Zaid
 */
@Named(value = "notificationBean")
@SessionScoped
public class NotificationBean implements Serializable {

    @EJB
    private NotificationService notificationService;
    private List<NotificationDto> notificationDtos;
    private DataModel<NotificationDto> dataModel;

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public List<NotificationDto> getNotificationDtos() {
        return notificationDtos;
    }

    public void setNotificationDtos(List<NotificationDto> notificationDtos) {
        this.notificationDtos = notificationDtos;
    }

    public DataModel<NotificationDto> getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel<NotificationDto> dataModel) {
        this.dataModel = dataModel;
    }

    @PostConstruct
    public void getNotification() {
        try {
            UserDto user = SessionUtil.getUser();
            notificationDtos = notificationService.getNotifications(user);
            //You need to filter the list and get only the seen column=0 and other values in anothr table
            dataModel = new CollectionDataModel<>(notificationDtos);
        } catch (ServiceException ex) {
            Logger.getLogger(NotificationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNotificationAsRead() {
        try {
            NotificationDto notificationDto = dataModel.getRowData();
            notificationService.setNotificationAsRead(notificationDto.getId());
        } catch (ServiceException ex) {
            Logger.getLogger(NotificationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
