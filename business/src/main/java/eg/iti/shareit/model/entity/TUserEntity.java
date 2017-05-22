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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adel Zaid
 */
@Entity
@Table(name = "T_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUserEntity.findAll", query = "SELECT t FROM TUserEntity t"),
    @NamedQuery(name = "TUserEntity.findById", query = "SELECT t FROM TUserEntity t WHERE t.id = :id"),
    @NamedQuery(name = "TUserEntity.findByUsername", query = "SELECT t FROM TUserEntity t WHERE t.username = :username"),
    @NamedQuery(name = "TUserEntity.findByEmail", query = "SELECT t FROM TUserEntity t WHERE t.email = :email"),
    @NamedQuery(name = "TUserEntity.findByPassword", query = "SELECT t FROM TUserEntity t WHERE t.password = :password"),
    @NamedQuery(name = "TUserEntity.findByImageUrl", query = "SELECT t FROM TUserEntity t WHERE t.imageUrl = :imageUrl"),
    @NamedQuery(name = "TUserEntity.findByPoints", query = "SELECT t FROM TUserEntity t WHERE t.points = :points")})
public class TUserEntity implements Serializable, GenericEntity {

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
    @Column(name = "USERNAME")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 200)
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POINTS")
    private BigInteger points;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toUser")
    private Collection<TActivityEntity> tActivityEntityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private Collection<TActivityEntity> tActivityEntityCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rUser")
    private Collection<TAddressEntity> tAddressEntityCollection;
    @JoinColumn(name = "GENDER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TGenderEntity gender;

    public TUserEntity() {
    }

    public TUserEntity(BigDecimal id) {
        this.id = id;
    }

    public TUserEntity(BigDecimal id, String username, String email, String password, BigInteger points) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.points = points;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    @XmlTransient
    public Collection<TActivityEntity> getTActivityEntityCollection1() {
        return tActivityEntityCollection1;
    }

    public void setTActivityEntityCollection1(Collection<TActivityEntity> tActivityEntityCollection1) {
        this.tActivityEntityCollection1 = tActivityEntityCollection1;
    }

    @XmlTransient
    public Collection<TAddressEntity> getTAddressEntityCollection() {
        return tAddressEntityCollection;
    }

    public void setTAddressEntityCollection(Collection<TAddressEntity> tAddressEntityCollection) {
        this.tAddressEntityCollection = tAddressEntityCollection;
    }

    public TGenderEntity getGender() {
        return gender;
    }

    public void setGender(TGenderEntity gender) {
        this.gender = gender;
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
        if (!(object instanceof TUserEntity)) {
            return false;
        }
        TUserEntity other = (TUserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.TUserEntity[ id=" + id + " ]";
    }

}
