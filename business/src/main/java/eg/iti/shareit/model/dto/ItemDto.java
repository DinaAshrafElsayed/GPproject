/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.dto;

import eg.iti.shareit.common.dto.GenericDto;
import java.sql.Date;

/**
 *
 * @author Yousef
 */
public class ItemDto implements java.io.Serializable, GenericDto{
    private int id;
    private String name;
    private String description;
    private CategoryDto category;
    private int isAvailable;
    private Date publishDate;
    private int points;

    public ItemDto() {
    }

    public ItemDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ItemDto(int id, String name, String description, CategoryDto category, int isAvailable, Date publishDate, int points) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.isAvailable = isAvailable;
        this.publishDate = publishDate;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
}
