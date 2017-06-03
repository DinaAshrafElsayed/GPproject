/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import static com.sun.javafx.logging.PulseLogger.addMessage;
import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.service.ActivityService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Adel Zaid
 */
@Named(value = "requests")
@SessionScoped
public class Requests implements Serializable {

    @Inject
    private ActivityService activityService;
    private List<ActivityDto> activityDtos;
    private List<ActivityDto> selectedActivityDto;

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

    public List<ActivityDto> getSelectedActivityDto() {
        return selectedActivityDto;
    }

    public void setSelectedActivityDto(List<ActivityDto> selectedActivityDto) {
        this.selectedActivityDto = selectedActivityDto;
    }

    @PostConstruct
    public void init() {
        try {
            if (activityService.getAllActivities() != null) {
                activityDtos = activityService.getAllActivities();
            }
        } catch (ServiceException ex) {
            Logger.getLogger(Requests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveChanges() {
        try {
            activityService.acceptRequest(selectedActivityDto.get(0));
        } catch (ServiceException ex) {
            Logger.getLogger(Requests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
