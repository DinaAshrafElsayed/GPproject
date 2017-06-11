/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.managedbeans;

import eg.iti.shareit.common.Exception.ServiceException;
import eg.iti.shareit.model.dto.ActivityDto;
import eg.iti.shareit.model.dto.CategoryDto;
import eg.iti.shareit.model.dto.ItemDto;
import eg.iti.shareit.service.ActivityService;
import eg.iti.shareit.service.CategoryService;
import eg.iti.shareit.service.ItemService;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Yousef
 */
@Named(value = "itemDetailBean")
@RequestScoped
public class ItemDetailBean implements Serializable {

    /**
     * Creates a new instance of ItemDetailBean
     */
    private ItemDto item;
    @EJB
    private ActivityService activityService;
    
    @EJB
    private ItemService itemService;
    
    @EJB
    private CategoryService categoryService;
    
    @Inject
    private UserBean user;
    
    private int id;
    private long publishDays;
    
    private String timeFrom;
    private String timeTo;
    private String meetingPoint;
    private boolean isRequested;
    private ActivityDto activity;
    private boolean noRequest;
    private List<ItemDto> relatedItems;
    private String message;
    private String todayString;
    private String url;
    
    private CategoryDto category;
    private String description;
    private String imageUrl;
    private int isAvailabe;
    private String name;
    private int points;
    private String tags;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public ActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getIsAvailabe() {
        return isAvailabe;
    }

    public void setIsAvailabe(int isAvailabe) {
        this.isAvailabe = isAvailabe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    

    public String getTodayString() {
        return todayString;
    }

    public void setTodayString(String todayString) {
        this.todayString = todayString;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public List<ItemDto> getRelatedItems() {
        return relatedItems;
    }

    public void setRelatedItems(List<ItemDto> relatedItems) {
        this.relatedItems = relatedItems;
    }

    
    

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
            
            activity = activityService.getActivityOfMyItem(item.getId().intValue(), user.getUserDto().getId().intValue());
            if(activity != null ){
                isRequested = true;
                if(activity.getStatus().getId().intValue() == 2){
                    message = "Your Request to the item has been accepted";
                    noRequest = true;
                }
                if(activity.getStatus().getId().intValue() == 3){
                    message = "Your Request to the item has been declined";
                    noRequest = true;
                }
            }
            
             if(user.getUserDto().getPoints() < item.getPoints()){
                    message = "You don't have enough points";
                    noRequest = true;
                }
            
            
            
            relatedItems = itemService.getRelatedItems(item);
            todayString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isNoRequest() {
        return noRequest;
    }

    public void setNoRequest(boolean noRequest) {
        this.noRequest = noRequest;
    }

    public ActivityDto getActivity() {
        return activity;
    }

    public void setActivity(ActivityDto activity) {
        this.activity = activity;
    }
    
    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public boolean isIsRequested() {
        return isRequested;
    }

    public void setIsRequested(boolean isRequested) {
        this.isRequested = isRequested;
    }


    public String requestItem(String timeFrom,String timeTo,String meetingPoint){
        Date timeFromDate,timeToDate,todayDate;
        try {  
            timeFromDate = new SimpleDateFormat("dd-MM-yyyy").parse(timeFrom);
            timeToDate = new SimpleDateFormat("dd-MM-yyyy").parse(timeTo);
            
//            
//            todayDate = new SimpleDateFormat("dd-MM-yyyy").parse(todayString);
//            
            boolean error = false;
            if(timeFromDate.compareTo(timeToDate) > 0){
                FacesMessage facesMessage = new FacesMessage("to date must be after from date");
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.addMessage("detailForm:timeTo", facesMessage);
                error = true;
            }
            
//            if(timeFromDate.compareTo(todayDate) < 0 ){
//                FacesMessage facesMessage = new FacesMessage("from date can't be before today ");
//                FacesContext facesContext = FacesContext.getCurrentInstance();
//                facesContext.addMessage("detailForm:timeFrom", facesMessage);
//                error = true;
//            }
            
            if(error)
                return "";
            
                
                
            boolean result = activityService.requestItem(item.getId().intValue(),  user.getUserDto().getId().intValue(),item.getUserFrom().getId().intValue(), timeFromDate, timeToDate, meetingPoint);
            System.out.println("===================== ######## item requested ! "+result);
            isRequested = true;
        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public void cancelRequest(){
        if(activity != null){
            try {
                String result = activityService.declineRequest(activity.getId().intValue());
                if(result != null)
                    isRequested = false;
            } catch (ServiceException ex) {
                Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void validateDateFrom(FacesContext context, UIComponent component, Object value){
    
    }
//    public void validateDateTo(FacesContext context, UIComponent component, Object value){
//        try {
//            Date timeFromDate = new SimpleDateFormat("dd-MM-yyyy").parse((String) value);
//            
//            long diff = date1.getTime() - date2.getTime();
//            publishDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//        } catch (ParseException ex) {
//            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void updateItem(){
        try {
            String categoryName = item.getCategory().getName();
            CategoryDto categoryDto = categoryService.getCategoryByName(categoryName);
            item.setCategory(categoryDto);
            item.setDescription(description);
            item.setImageUrl(imageUrl);
            item.setIsAvailable(isAvailabe);
            item.setName(name);
            item.setPoints(points);
            item.setTags(tags);
            itemService.updateSharedItem(item);
        } catch (ServiceException ex) {
            Logger.getLogger(ItemDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
