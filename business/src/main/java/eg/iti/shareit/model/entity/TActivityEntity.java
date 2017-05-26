/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

import eg.iti.shareit.common.entity.GenericEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adel Zaid
 */
@Entity
@Table(name = "T_ACTIVITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TActivityEntity.findAll", query = "SELECT t FROM TActivityEntity t"),
    @NamedQuery(name = "TActivityEntity.findById", query = "SELECT t FROM TActivityEntity t WHERE t.id = :id"),
    @NamedQuery(name = "TActivityEntity.findByMeetingPoint", query = "SELECT t FROM TActivityEntity t WHERE t.meetingPoint = :meetingPoint"),
    @NamedQuery(name = "TActivityEntity.findByStatus", query = "SELECT t FROM TActivityEntity t WHERE t.status = :status"),
    @NamedQuery(name = "TActivityEntity.findByTimeFrom", query = "SELECT t FROM TActivityEntity t WHERE t.timeFrom = :timeFrom"),
    @NamedQuery(name = "TActivityEntity.findByTimeTo", query = "SELECT t FROM TActivityEntity t WHERE t.timeTo = :timeTo"),
    @NamedQuery(name = "TActivityEntity.findByActivityDeleted", query = "SELECT t FROM TActivityEntity t WHERE t.activityDeleted = :activityDeleted")})
public class TActivityEntity implements Serializable, GenericEntity {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "MEETING_POINT")
    private String meetingPoint;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIME_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIME_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVITY_DELETED")
    private short activityDeleted;
    @JoinColumn(name = "ITEM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TItemEntity item;
    @JoinColumn(name = "TO_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TUserEntity toUser;
    @JoinColumn(name = "FROM_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TUserEntity fromUser;

    public TActivityEntity() {
    }

    public TActivityEntity(BigDecimal id) {
        this.id = id;
    }

    public TActivityEntity(BigDecimal id, String meetingPoint, String status, Date timeFrom, Date timeTo, short activityDeleted) {
        this.id = id;
        this.meetingPoint = meetingPoint;
        this.status = status;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.activityDeleted = activityDeleted;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public short getActivityDeleted() {
        return activityDeleted;
    }

    public void setActivityDeleted(short activityDeleted) {
        this.activityDeleted = activityDeleted;
    }

    public TItemEntity getItem() {
        return item;
    }

    public void setItem(TItemEntity item) {
        this.item = item;
    }

    public TUserEntity getToUser() {
        return toUser;
    }

    public void setToUser(TUserEntity toUser) {
        this.toUser = toUser;
    }

    public TUserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(TUserEntity fromUser) {
        this.fromUser = fromUser;
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
        if (!(object instanceof TActivityEntity)) {
            return false;
        }
        TActivityEntity other = (TActivityEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.TActivityEntity[ id=" + id + " ]";
    }

}
