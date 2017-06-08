/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.service.ActivityService;
import eg.iti.shareit.service.ItemService;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Yousef
 */
@Named(value = "itemDetailBean")
@ViewScoped
public class ItemDetailBean implements Serializable {

    /**
     * Creates a new instance of ItemDetailBean
     */
    private ItemDto item;
    @EJB
    private ActivityService activityService;
    
    private int id;
    private long publishDays;
    
    private String timeFrom;
    private String timeTo;
    private String meetingPoint;

    
    @EJB
    private ItemService itemService;

    public ItemDetailBean() {
    }

    @PostConstruct
    public void init() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            id = Integer.parseInt(request.getParameter("id"));
            item = itemService.getItemById(id);


            Date date1 = new Date();
            Date date2 = item.getPublishDate();
            long diff = date1.getTime() - date2.getTime();
            publishDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPublishDays() {
        return publishDays;
    }

    public void setPublishDays(long publishDays) {
        this.publishDays = publishDays;
    }

    public String getTimeFrom() {
        System.out.println("////////////////////// gettt time frommmm");
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        System.out.println("////////////////////// time frommmm");
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
    
   
   
    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }


    public String requestItem(String timeFrom,String timeTo,String meetingPoint){
        Date timeFromDate,timeToDate;
        try {  
            timeFromDate = new SimpleDateFormat("dd-MM-yyyy").parse(timeFrom);
            timeToDate = new SimpleDateFormat("dd-MM-yyyy").parse(timeTo);
        
            boolean result = activityService.requestItem(item.getId().intValue(), item.getUserFrom().getId().intValue(), 41, timeFromDate, timeToDate, meetingPoint);
            System.out.println("===================== ######## item requested ! "+result);
        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
