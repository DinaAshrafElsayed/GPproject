package eg.iti.shareit.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_GENDER", schema = "SHAREIT", catalog = "")
public class TGenderEntity {
    private long id;
    private String gender;
    private Collection<TUserEntity> tUsersById;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TGenderEntity that = (TGenderEntity) o;

        if (id != that.id) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tGenderByGender")
    public Collection<TUserEntity> gettUsersById() {
        return tUsersById;
    }

    public void settUsersById(Collection<TUserEntity> tUsersById) {
        this.tUsersById = tUsersById;
    }
}
