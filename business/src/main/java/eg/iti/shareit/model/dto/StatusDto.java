/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import java.math.BigDecimal;

/**
 *
 * @author Adel Zaid
 */
public class StatusDto {

    private BigDecimal id;
    private String status;

    public StatusDto() {
    }

    public StatusDto(BigDecimal id, String status) {
        this.id = id;
        this.status = status;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}