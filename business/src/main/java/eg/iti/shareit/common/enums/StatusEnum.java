/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.common.enums;

import eg.iti.shareit.model.dto.StatusDto;

/**
 *
 * @author Adel Zaid
 */
public enum StatusEnum {
    PENDING(new StatusDto(1, "Pending")), ACCEPTED(new StatusDto(2, "Accepted")), DECLINED(new StatusDto(3, "Declined"));

    private StatusEnum(StatusDto status) {
        this.status = status;

    }

    private StatusDto status;

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

}
