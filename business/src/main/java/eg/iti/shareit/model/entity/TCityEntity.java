package eg.iti.shareit.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_CITY", schema = "SHAREIT", catalog = "")
public class TCityEntity {
    private long id;
    private String city;
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
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCityEntity that = (TCityEntity) o;

        if (id != that.id) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tCityByCity")
    public Collection<TAddressEntity> gettAddressesById() {
        return tAddressesById;
    }

    public void settAddressesById(Collection<TAddressEntity> tAddressesById) {
        this.tAddressesById = tAddressesById;
    }
}
