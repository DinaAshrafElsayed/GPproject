package eg.iti.shareit.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_CATEGORY", schema = "SHAREIT", catalog = "")
public class TCategoryEntity {
    private long id;
    private String name;
    private long maxPoints;
    private Collection<TItemEntity> tItemsById;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "MAX_POINTS")
    public long getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(long maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCategoryEntity that = (TCategoryEntity) o;

        if (id != that.id) return false;
        if (maxPoints != that.maxPoints) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (maxPoints ^ (maxPoints >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "tCategoryByCategory")
    public Collection<TItemEntity> gettItemsById() {
        return tItemsById;
    }

    public void settItemsById(Collection<TItemEntity> tItemsById) {
        this.tItemsById = tItemsById;
    }
}
