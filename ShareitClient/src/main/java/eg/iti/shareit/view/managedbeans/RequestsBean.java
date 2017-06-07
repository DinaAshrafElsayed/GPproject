/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.UserDto;
import eg.iti.shareit.service.ActivityService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.inject.Named;

/**
 *
 * @author Adel Zaid
 */
@Named(value = "requests")
@javax.faces.view.ViewScoped
public class RequestsBean implements Serializable {

    @EJB
    private ActivityService activityService;
    private List<ActivityDto> activityDtos;
    private DataModel<ActivityDto> dataModel;

    public ActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    public List<ActivityDto> getActivityDtos() {
        return activityDtos;
    }

    public void setActivityDtos(List<ActivityDto> activityDtos) {
        this.activityDtos = activityDtos;
    }

    public DataModel<ActivityDto> getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel<ActivityDto> dataModel) {
        this.dataModel = dataModel;
    }

    @PostConstruct
    public void init() {
        try {
            UserDto user = SessionUtil.getUser();
            activityDtos = activityService.getAllActivities(user);
            if (activityDtos != null) {

                dataModel = new CollectionDataModel<>(activityDtos);
            }
        } catch (ServiceException ex) {
            Logger.getLogger(RequestsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void acceptRequest() {
        try {
            ActivityDto rowData = dataModel.getRowData();
            activityService.acceptRequest(rowData);
            init();
        } catch (ServiceException ex) {
            Logger.getLogger(RequestsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void declineRequest() {
        try {

            ActivityDto activityDto = dataModel.getRowData();
            activityService.declineRequest((activityDto.getId()).intValue());
            init();
        } catch (ServiceException ex) {
            Logger.getLogger(RequestsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
