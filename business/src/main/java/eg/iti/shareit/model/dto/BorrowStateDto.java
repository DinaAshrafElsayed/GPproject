/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;
import eg.iti.shareit.model.entity.ActivityEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Adel Zaid
 */
public class BorrowStateDto implements Serializable, GenericDto {

    private BigDecimal id;
    private BigInteger isBack;
    private ActivityDto activityDto;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getIsBack() {
        return isBack;
    }

    public void setIsBack(BigInteger isBack) {
        this.isBack = isBack;
    }

    public ActivityDto getActivityDto() {
        return activityDto;
    }

    public void setActivityDto(ActivityDto activityDto) {
        this.activityDto = activityDto;
    }

}
