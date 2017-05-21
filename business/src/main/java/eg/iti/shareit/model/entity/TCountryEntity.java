package eg.iti.shareit.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_COUNTRY", schema = "SHAREIT", catalog = "")
public class TCountryEntity {
    private long id;
    private String country;
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
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCountryEntity that = (TCountryEntity) o;

        if (id != that.id) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tCountryByCountry")
    public Collection<TAddressEntity> gettAddressesById() {
        return tAddressesById;
    }

    public void settAddressesById(Collection<TAddressEntity> tAddressesById) {
        this.tAddressesById = tAddressesById;
    }
}
