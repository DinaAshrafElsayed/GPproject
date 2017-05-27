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
import java.util.List;
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
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT t FROM UserEntity t"),
    @NamedQuery(name = "UserEntity.findById", query = "SELECT t FROM UserEntity t WHERE t.id = :id"),
    @NamedQuery(name = "UserEntity.findByUsername", query = "SELECT t FROM UserEntity t WHERE t.username = :username"),
    @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT t FROM UserEntity t WHERE t.email = :email"),
    @NamedQuery(name = "UserEntity.findByPassword", query = "SELECT t FROM UserEntity t WHERE t.password = :password"),
    @NamedQuery(name = "UserEntity.findByImageUrl", query = "SELECT t FROM UserEntity t WHERE t.imageUrl = :imageUrl"),
    @NamedQuery(name = "UserEntity.findByPoints", query = "SELECT t FROM UserEntity t WHERE t.points = :points")})
public class UserEntity implements Serializable, GenericEntity {

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
    private List<ActivityEntity> toActivityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUser")
    private List<ActivityEntity> fromActivityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rUser")
    private List<AddressEntity> addressList;
    @JoinColumn(name = "GENDER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private GenderEntity gender;

    public UserEntity() {
    }

    public UserEntity(BigDecimal id) {
        this.id = id;
    }

    public UserEntity(BigDecimal id, String username, String email, String password, BigInteger points) {
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
    public List<ActivityEntity> getToActivityList() {
        return toActivityList;
    }

    public void setToActivityList(List<ActivityEntity> toActivityList) {
        this.toActivityList = toActivityList;
    }

    @XmlTransient
    public List<ActivityEntity> getFromActivityList() {
        return fromActivityList;
    }

    public void setFromActivityList(List<ActivityEntity> fromActivityList) {
        this.fromActivityList = fromActivityList;
    }

    @XmlTransient
    public List<AddressEntity> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressEntity> addressList) {
        this.addressList = addressList;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public void setGender(GenderEntity gender) {
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.UserEntity[ id=" + id + " ]";
    }

}
