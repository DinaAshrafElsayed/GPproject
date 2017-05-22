/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

import eg.iti.shareit.common.entity.GenericEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adel Zaid
 */
@Entity
@Table(name = "T_ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TAddressEntity.findAll", query = "SELECT t FROM TAddressEntity t"),
    @NamedQuery(name = "TAddressEntity.findById", query = "SELECT t FROM TAddressEntity t WHERE t.id = :id")})
public class TAddressEntity implements Serializable, GenericEntity {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "CITY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TCityEntity city;
    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TCountryEntity country;
    @JoinColumn(name = "STATE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TStateEntity state;
    @JoinColumn(name = "R_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TUserEntity rUser;

    public TAddressEntity() {
    }

    public TAddressEntity(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public TCityEntity getCity() {
        return city;
    }

    public void setCity(TCityEntity city) {
        this.city = city;
    }

    public TCountryEntity getCountry() {
        return country;
    }

    public void setCountry(TCountryEntity country) {
        this.country = country;
    }

    public TStateEntity getState() {
        return state;
    }

    public void setState(TStateEntity state) {
        this.state = state;
    }

    public TUserEntity getRUser() {
        return rUser;
    }

    public void setRUser(TUserEntity rUser) {
        this.rUser = rUser;
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
        if (!(object instanceof TAddressEntity)) {
            return false;
        }
        TAddressEntity other = (TAddressEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.TAddressEntity[ id=" + id + " ]";
    }

}
