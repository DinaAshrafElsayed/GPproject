package eg.iti.shareit.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_ITEM", schema = "SHAREIT", catalog = "")
public class TItemEntity {
    private long id;
    private String name;
    private String description;
    private long category;
    private boolean isAvailable;
    private Timestamp publishDate;
    private long points;
    private Collection<TActivityEntity> tActivitiesById;
    private TCategoryEntity tCategoryByCategory;

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
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CATEGORY")
    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    @Basic
    @Column(name = "IS_AVAILABLE")
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Basic
    @Column(name = "PUBLISH_DATE")
    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "POINTS")
    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TItemEntity that = (TItemEntity) o;

        if (id != that.id) return false;
        if (category != that.category) return false;
        if (isAvailable != that.isAvailable) return false;
        if (points != that.points) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (category ^ (category >>> 32));
        result = 31 * result + (isAvailable ? 1 : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (int) (points ^ (points >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "tItemByItem")
    public Collection<TActivityEntity> gettActivitiesById() {
        return tActivitiesById;
    }

    public void settActivitiesById(Collection<TActivityEntity> tActivitiesById) {
        this.tActivitiesById = tActivitiesById;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID", nullable = false)
    public TCategoryEntity gettCategoryByCategory() {
        return tCategoryByCategory;
    }

    public void settCategoryByCategory(TCategoryEntity tCategoryByCategory) {
        this.tCategoryByCategory = tCategoryByCategory;
    }
}
