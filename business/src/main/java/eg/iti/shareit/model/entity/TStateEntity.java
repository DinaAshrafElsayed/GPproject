package eg.iti.shareit.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_STATE", schema = "SHAREIT", catalog = "")
public class TStateEntity {
    private long id;
    private String state;
    private Collection<TAddressEntity> tAddressesById;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TStateEntity that = (TStateEntity) o;

        if (id != that.id) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tStateByState")
    public Collection<TAddressEntity> gettAddressesById() {
        return tAddressesById;
    }

    public void settAddressesById(Collection<TAddressEntity> tAddressesById) {
        this.tAddressesById = tAddressesById;
    }
}
