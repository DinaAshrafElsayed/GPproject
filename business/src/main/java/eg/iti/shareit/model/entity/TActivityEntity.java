package eg.iti.shareit.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by adelz on 5/21/2017.
 */
@Entity
@Table(name = "T_ACTIVITY", schema = "SHAREIT", catalog = "")
public class TActivityEntity {
    private long id;
    private long item;
    private long fromUser;
    private long toUser;
    private String meetingPoint;
    private String status;
    private Timestamp timeFrom;
    private Timestamp timeTo;
    private boolean activityDeleted;
    private TItemEntity tItemByItem;
    private TUserEntity tUserByFromUser;
    private TUserEntity tUserByToUser;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEM")
    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    @Basic
    @Column(name = "FROM_USER")
    public long getFromUser() {
        return fromUser;
    }

    public void setFromUser(long fromUser) {
        this.fromUser = fromUser;
    }

    @Basic
    @Column(name = "TO_USER")
    public long getToUser() {
        return toUser;
    }

    public void setToUser(long toUser) {
        this.toUser = toUser;
    }

    @Basic
    @Column(name = "MEETING_POINT")
    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "TIME_FROM")
    public Timestamp getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Timestamp timeFrom) {
        this.timeFrom = timeFrom;
    }

    @Basic
    @Column(name = "TIME_TO")
    public Timestamp getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Timestamp timeTo) {
        this.timeTo = timeTo;
    }

    @Basic
    @Column(name = "ACTIVITY_DELETED")
    public boolean isActivityDeleted() {
        return activityDeleted;
    }

    public void setActivityDeleted(boolean activityDeleted) {
        this.activityDeleted = activityDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TActivityEntity that = (TActivityEntity) o;

        if (id != that.id) return false;
        if (item != that.item) return false;
        if (fromUser != that.fromUser) return false;
        if (toUser != that.toUser) return false;
        if (activityDeleted != that.activityDeleted) return false;
        if (meetingPoint != null ? !meetingPoint.equals(that.meetingPoint) : that.meetingPoint != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (timeFrom != null ? !timeFrom.equals(that.timeFrom) : that.timeFrom != null) return false;
        if (timeTo != null ? !timeTo.equals(that.timeTo) : that.timeTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (item ^ (item >>> 32));
        result = 31 * result + (int) (fromUser ^ (fromUser >>> 32));
        result = 31 * result + (int) (toUser ^ (toUser >>> 32));
        result = 31 * result + (meetingPoint != null ? meetingPoint.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (timeFrom != null ? timeFrom.hashCode() : 0);
        result = 31 * result + (timeTo != null ? timeTo.hashCode() : 0);
        result = 31 * result + (activityDeleted ? 1 : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ITEM", referencedColumnName = "ID", nullable = false)
    public TItemEntity gettItemByItem() {
        return tItemByItem;
    }

    public void settItemByItem(TItemEntity tItemByItem) {
        this.tItemByItem = tItemByItem;
    }

    @ManyToOne
    @JoinColumn(name = "FROM_USER", referencedColumnName = "ID", nullable = false)
    public TUserEntity gettUserByFromUser() {
        return tUserByFromUser;
    }

    public void settUserByFromUser(TUserEntity tUserByFromUser) {
        this.tUserByFromUser = tUserByFromUser;
    }

    @ManyToOne
    @JoinColumn(name = "TO_USER", referencedColumnName = "ID", nullable = false)
    public TUserEntity gettUserByToUser() {
        return tUserByToUser;
    }

    public void settUserByToUser(TUserEntity tUserByToUser) {
        this.tUserByToUser = tUserByToUser;
    }
}
