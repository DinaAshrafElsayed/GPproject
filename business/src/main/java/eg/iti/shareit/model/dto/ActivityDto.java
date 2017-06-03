
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Yousef
 */
public class ActivityDto implements java.io.Serializable, GenericDto {

    private BigDecimal id;
    private ItemDto item;
    private UserDto fromUser;
    private UserDto toUser;
    private String meetingPoint;
    private StatusDto status;
    private Date timeFrom;
    private Date timeTo;
    private int activityDeleted;

    public ActivityDto() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public UserDto getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserDto fromUser) {
        this.fromUser = fromUser;
    }

    public UserDto getToUser() {
        return toUser;
    }

    public void setToUser(UserDto toUser) {
        this.toUser = toUser;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public int getActivityDeleted() {
        return activityDeleted;
    }

    public void setActivityDeleted(int activityDeleted) {
        this.activityDeleted = activityDeleted;
    }

    public int calculateIntervalOfTime() {
        //        Calendar calendar = Calendar.getInstance();
        //        calendar.setTime(timeTo);
        //        int daysTo = calendar.get(Calendar.DAY_OF_MONTH);
        //        calendar.setTime(timeFrom);
        //        int daysFrom = calendar.get(Calendar.DAY_OF_MONTH);
        //        return daysTo - daysFrom;
        long diff = timeTo.getTime() - timeFrom.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return (int) days;
    }
}
