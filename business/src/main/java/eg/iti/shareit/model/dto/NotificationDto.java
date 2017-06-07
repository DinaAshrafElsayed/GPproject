/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;
import eg.iti.shareit.model.entity.ItemEntity;
import eg.iti.shareit.model.entity.UserEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Adel Zaid
 */
public class NotificationDto implements Serializable, GenericDto {

    private BigDecimal id;
    private BigInteger pointsDeducted;
    private BigInteger days;
    private String meetingPoint;
    private ItemEntity item;
    private UserEntity fromUser;
    private UserEntity toUser;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getPointsDeducted() {
        return pointsDeducted;
    }

    public void setPointsDeducted(BigInteger pointsDeducted) {
        this.pointsDeducted = pointsDeducted;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    public UserEntity getToUser() {
        return toUser;
    }

    public void setToUser(UserEntity toUser) {
        this.toUser = toUser;
    }

    public BigInteger getDays() {
        return days;
    }

    public void setDays(BigInteger days) {
        this.days = days;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

   
}
