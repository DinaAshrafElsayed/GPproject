package eg.iti.shareit.model.entity;

import eg.iti.shareit.common.entity.GenericEntity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_USER", schema = "SHAREIT", catalog = "")
public class TUserEntity implements GenericEntity {
    private long id;
    private String username;
    private String email;
    private String password;
    private String imageUrl;
    private long points;
    private long gender;
    private Collection<TActivityEntity> tActivitiesById;
    private Collection<TActivityEntity> tActivitiesById_0;
    private Collection<TAddressEntity> tAddressesById;
    private TGenderEntity tGenderByGender;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "POINTS")
    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    @Basic
    @Column(name = "GENDER")
    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TUserEntity that = (TUserEntity) o;

        if (id != that.id) return false;
        if (points != that.points) return false;
        if (gender != that.gender) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (int) (points ^ (points >>> 32));
        result = 31 * result + (int) (gender ^ (gender >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "tUserByFromUser")
    public Collection<TActivityEntity> gettActivitiesById() {
        return tActivitiesById;
    }

    public void settActivitiesById(Collection<TActivityEntity> tActivitiesById) {
        this.tActivitiesById = tActivitiesById;
    }

    @OneToMany(mappedBy = "tUserByToUser")
    public Collection<TActivityEntity> gettActivitiesById_0() {
        return tActivitiesById_0;
    }

    public void settActivitiesById_0(Collection<TActivityEntity> tActivitiesById_0) {
        this.tActivitiesById_0 = tActivitiesById_0;
    }

    @OneToMany(mappedBy = "tUserByRUser")
    public Collection<TAddressEntity> gettAddressesById() {
        return tAddressesById;
    }

    public void settAddressesById(Collection<TAddressEntity> tAddressesById) {
        this.tAddressesById = tAddressesById;
    }

    @ManyToOne
    @JoinColumn(name = "GENDER", referencedColumnName = "ID", nullable = false)
    public TGenderEntity gettGenderByGender() {
        return tGenderByGender;
    }

    public void settGenderByGender(TGenderEntity tGenderByGender) {
        this.tGenderByGender = tGenderByGender;
    }
}
