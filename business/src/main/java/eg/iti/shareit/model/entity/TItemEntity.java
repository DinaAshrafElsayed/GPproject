/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

import eg.iti.shareit.common.entity.GenericEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 * @author Adel Zaid
 */
@Entity
@Table(name = "T_ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TItemEntity.findAll", query = "SELECT t FROM TItemEntity t"),
    @NamedQuery(name = "TItemEntity.findById", query = "SELECT t FROM TItemEntity t WHERE t.id = :id"),
    @NamedQuery(name = "TItemEntity.findByName", query = "SELECT t FROM TItemEntity t WHERE t.name = :name"),
    @NamedQuery(name = "TItemEntity.findByDescription", query = "SELECT t FROM TItemEntity t WHERE t.description = :description"),
    @NamedQuery(name = "TItemEntity.findByIsAvailable", query = "SELECT t FROM TItemEntity t WHERE t.isAvailable = :isAvailable"),
    @NamedQuery(name = "TItemEntity.findByPublishDate", query = "SELECT t FROM TItemEntity t WHERE t.publishDate = :publishDate"),
    @NamedQuery(name = "TItemEntity.findByPoints", query = "SELECT t FROM TItemEntity t WHERE t.points = :points")})
public class TItemEntity implements Serializable, GenericEntity {

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
    private short isAvailable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POINTS")
    private BigInteger points;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Collection<TActivityEntity> tActivityEntityCollection;
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TCategoryEntity category;

    public TItemEntity() {
    }

    public TItemEntity(BigDecimal id) {
        this.id = id;
    }

    public TItemEntity(BigDecimal id, String name, short isAvailable, Date publishDate, BigInteger points) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.publishDate = publishDate;
        this.points = points;
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

    public short getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(short isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public BigInteger getPoints() {
        return points;
    }

    public void setPoints(BigInteger points) {
        this.points = points;
    }

    @XmlTransient
    public Collection<TActivityEntity> getTActivityEntityCollection() {
        return tActivityEntityCollection;
    }

    public void setTActivityEntityCollection(Collection<TActivityEntity> tActivityEntityCollection) {
        this.tActivityEntityCollection = tActivityEntityCollection;
    }

    public TCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(TCategoryEntity category) {
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
        if (!(object instanceof TItemEntity)) {
            return false;
        }
        TItemEntity other = (TItemEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.TItemEntity[ id=" + id + " ]";
    }

}
