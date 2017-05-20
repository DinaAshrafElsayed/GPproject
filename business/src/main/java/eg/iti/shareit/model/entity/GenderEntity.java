/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

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
 * @author Yousef
 */
@Entity
@Table(name = "T_GENDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenderEntity.findAll", query = "SELECT g FROM GenderEntity g"),
    @NamedQuery(name = "GenderEntity.findById", query = "SELECT g FROM GenderEntity g WHERE g.id = :id"),
    @NamedQuery(name = "GenderEntity.findByGender", query = "SELECT g FROM GenderEntity g WHERE g.gender = :gender")})
public class GenderEntity implements Serializable {

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
    @Column(name = "GENDER")
    private String gender;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gender")
    private Collection<UserEntity> userEntityCollection;

    public GenderEntity() {
    }

    public GenderEntity(BigDecimal id) {
        this.id = id;
    }

    public GenderEntity(BigDecimal id, String gender) {
        this.id = id;
        this.gender = gender;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlTransient
    public Collection<UserEntity> getUserEntityCollection() {
        return userEntityCollection;
    }

    public void setUserEntityCollection(Collection<UserEntity> userEntityCollection) {
        this.userEntityCollection = userEntityCollection;
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
        if (!(object instanceof GenderEntity)) {
            return false;
        }
        GenderEntity other = (GenderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.GenderEntity[ id=" + id + " ]";
    }
    
}
