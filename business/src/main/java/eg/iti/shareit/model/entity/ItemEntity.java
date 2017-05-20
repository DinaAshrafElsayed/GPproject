/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yousef
 */
@Entity
@Table(name = "T_ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemEntity.findAll", query = "SELECT i FROM ItemEntity i"),
    @NamedQuery(name = "ItemEntity.findById", query = "SELECT i FROM ItemEntity i WHERE i.id = :id"),
    @NamedQuery(name = "ItemEntity.findByName", query = "SELECT i FROM ItemEntity i WHERE i.name = :name"),
    @NamedQuery(name = "ItemEntity.findByDescription", query = "SELECT i FROM ItemEntity i WHERE i.description = :description"),
    @NamedQuery(name = "ItemEntity.findByIsAvailable", query = "SELECT i FROM ItemEntity i WHERE i.isAvailable = :isAvailable"),
    @NamedQuery(name = "ItemEntity.findByPublishDate", query = "SELECT i FROM ItemEntity i WHERE i.publishDate = :publishDate")})
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NAME")
    private String name;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_AVAILABLE")
    private Character isAvailable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<ActivityEntity> activityEntityCollection;
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CategoryEntity category;

    public ItemEntity() {
    }

    public ItemEntity(BigDecimal id) {
        this.id = id;
    }

    public ItemEntity(BigDecimal id, String name, Character isAvailable, Date publishDate) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.publishDate = publishDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Character isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @XmlTransient
    public Collection<ActivityEntity> getActivityEntityCollection() {
        return activityEntityCollection;
    }

    public void setActivityEntityCollection(Collection<ActivityEntity> activityEntityCollection) {
        this.activityEntityCollection = activityEntityCollection;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemEntity)) {
            return false;
        }
        ItemEntity other = (ItemEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.ItemEntity[ id=" + id + " ]";
    }
    
}
