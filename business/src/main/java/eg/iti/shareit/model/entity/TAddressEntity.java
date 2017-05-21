package eg.iti.shareit.model.entity;

import javax.persistence.*;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_ADDRESS", schema = "SHAREIT", catalog = "")
public class TAddressEntity {
    private long id;
    private long rUser;
    private long country;
    private long city;
    private long state;
    private TUserEntity tUserByRUser;
    private TCountryEntity tCountryByCountry;
    private TCityEntity tCityByCity;
    private TStateEntity tStateByState;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "R_USER")
    public long getrUser() {
        return rUser;
    }

    public void setrUser(long rUser) {
        this.rUser = rUser;
    }

    @Basic
    @Column(name = "COUNTRY")
    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }

    @Basic
    @Column(name = "CITY")
    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    @Basic
    @Column(name = "STATE")
    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAddressEntity that = (TAddressEntity) o;

        if (id != that.id) return false;
        if (rUser != that.rUser) return false;
        if (country != that.country) return false;
        if (city != that.city) return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (rUser ^ (rUser >>> 32));
        result = 31 * result + (int) (country ^ (country >>> 32));
        result = 31 * result + (int) (city ^ (city >>> 32));
        result = 31 * result + (int) (state ^ (state >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "R_USER", referencedColumnName = "ID", nullable = false)
    public TUserEntity gettUserByRUser() {
        return tUserByRUser;
    }

    public void settUserByRUser(TUserEntity tUserByRUser) {
        this.tUserByRUser = tUserByRUser;
    }

    @ManyToOne
    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID", nullable = false)
    public TCountryEntity gettCountryByCountry() {
        return tCountryByCountry;
    }

    public void settCountryByCountry(TCountryEntity tCountryByCountry) {
        this.tCountryByCountry = tCountryByCountry;
    }

    @ManyToOne
    @JoinColumn(name = "CITY", referencedColumnName = "ID", nullable = false)
    public TCityEntity gettCityByCity() {
        return tCityByCity;
    }

    public void settCityByCity(TCityEntity tCityByCity) {
        this.tCityByCity = tCityByCity;
    }

    @ManyToOne
    @JoinColumn(name = "STATE", referencedColumnName = "ID", nullable = false)
    public TStateEntity gettStateByState() {
        return tStateByState;
    }

    public void settStateByState(TStateEntity tStateByState) {
        this.tStateByState = tStateByState;
    }
}
