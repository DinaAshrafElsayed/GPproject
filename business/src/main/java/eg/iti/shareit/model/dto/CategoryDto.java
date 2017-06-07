/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;
import java.math.BigDecimal;

/**
 *
 * @author Yousef
 */
public class CategoryDto implements java.io.Serializable, GenericDto{
    private BigDecimal id;
    private String name;
    private int maxPoints;

    public CategoryDto() {
    }

    public CategoryDto(BigDecimal id, String name, int maxPoints) {
        this.id = id;
        this.name = name;
        this.maxPoints = maxPoints;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public String toString() {
        return "CategoryDto{" + "id=" + id + ", name=" + name + ", maxPoints=" + maxPoints + '}';
    }
    
}
