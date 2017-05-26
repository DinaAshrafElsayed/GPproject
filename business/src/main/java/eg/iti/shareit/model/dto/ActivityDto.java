/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;
import eg.iti.shareit.model.entity.TItemEntity;
import eg.iti.shareit.model.entity.TUserEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Adel Zaid
 */
public class ActivityDto implements GenericDto {

    private BigDecimal id;
    private String meetingPoint;
    private String status;
    private Date timeFrom;
    private Date timeTo;
    private short activityDeleted;
    private TItemEntity item;
    private TUserEntity toUser;
    private TUserEntity fromUser;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public short getActivityDeleted() {
        return activityDeleted;
    }

    public void setActivityDeleted(short activityDeleted) {
        this.activityDeleted = activityDeleted;
    }

    public TItemEntity getItem() {
        return item;
    }

    public void setItem(TItemEntity item) {
        this.item = item;
    }

    public TUserEntity getToUser() {
        return toUser;
    }

    public void setToUser(TUserEntity toUser) {
        this.toUser = toUser;
    }

    public TUserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(TUserEntity fromUser) {
        this.fromUser = fromUser;
    }

}
