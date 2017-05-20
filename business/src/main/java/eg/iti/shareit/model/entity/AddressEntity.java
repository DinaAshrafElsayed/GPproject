/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

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
 * @author Yousef
 */
@Entity
@Table(name = "T_ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AddressEntity.findAll", query = "SELECT a FROM AddressEntity a"),
    @NamedQuery(name = "AddressEntity.findById", query = "SELECT a FROM AddressEntity a WHERE a.id = :id")})
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @JoinColumn(name = "CITY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CityEntity city;
    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CountryEntity country;
    @JoinColumn(name = "STATE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private StateEntity state;
    @JoinColumn(name = "R_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UserEntity rUser;

    public AddressEntity() {
    }

    public AddressEntity(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }

    public UserEntity getRUser() {
        return rUser;
    }

    public void setRUser(UserEntity rUser) {
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
        if (!(object instanceof AddressEntity)) {
            return false;
        }
        AddressEntity other = (AddressEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.AddressEntity[ id=" + id + " ]";
    }
    
}
