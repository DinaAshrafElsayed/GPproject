/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

import eg.iti.shareit.common.entity.GenericEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adel Zaid
 */
@Entity
@Table(name = "T_COUNTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCountryEntity.findAll", query = "SELECT t FROM TCountryEntity t"),
    @NamedQuery(name = "TCountryEntity.findById", query = "SELECT t FROM TCountryEntity t WHERE t.id = :id"),
    @NamedQuery(name = "TCountryEntity.findByCountry", query = "SELECT t FROM TCountryEntity t WHERE t.country = :country")})
public class TCountryEntity implements Serializable, GenericEntity {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COUNTRY")
    private String country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Collection<TAddressEntity> tAddressEntityCollection;

    public TCountryEntity() {
    }

    public TCountryEntity(BigDecimal id) {
        this.id = id;
    }

    public TCountryEntity(BigDecimal id, String country) {
        this.id = id;
        this.country = country;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlTransient
    public Collection<TAddressEntity> getTAddressEntityCollection() {
        return tAddressEntityCollection;
    }

    public void setTAddressEntityCollection(Collection<TAddressEntity> tAddressEntityCollection) {
        this.tAddressEntityCollection = tAddressEntityCollection;
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
        if (!(object instanceof TCountryEntity)) {
            return false;
        }
        TCountryEntity other = (TCountryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.TCountryEntity[ id=" + id + " ]";
    }

}
