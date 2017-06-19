/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

import eg.iti.shareit.common.entity.GenericEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adel Zaid
 */
@Entity
@Table(name = "T_NOTIFICATION")
@XmlRootElement
public class NotificationEntity implements Serializable, GenericEntity {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_NOTIFICATION_SEQ")
    @SequenceGenerator(name = "T_NOTIFICATION_SEQ", sequenceName = "T_NOTIFICATION_SEQ", initialValue = 1)
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POINTS_DEDUCTED")
    private BigInteger pointsDeducted;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MEETING_POINT")
    private String meetingPoint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAYS")
    private BigInteger days;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEEN")
    private BigInteger seen;
    @javax.persistence.ManyToOne(fetch = FetchType.LAZY, optional = false)
    @javax.persistence.JoinColumn(name = "ITEM", referencedColumnName = "ID")
    private ItemEntity item;
    @javax.persistence.ManyToOne(fetch = FetchType.LAZY, optional = false)
    @javax.persistence.JoinColumn(name = "FROM_USER", referencedColumnName = "ID")
    private UserEntity fromUser;
    @javax.persistence.ManyToOne(fetch = FetchType.LAZY, optional = false)
    @javax.persistence.JoinColumn(name = "TO_USER", referencedColumnName = "ID")
    private UserEntity toUser;

    public NotificationEntity() {
    }

    public NotificationEntity(BigDecimal id) {
        this.id = id;
    }

    public NotificationEntity(BigDecimal id, BigInteger pointsDeducted, String meetingPoint, BigInteger days, BigInteger seen) {
        this.id = id;
        this.pointsDeducted = pointsDeducted;
        this.meetingPoint = meetingPoint;
        this.days = days;
        this.seen = seen;
    }

    public java.math.BigDecimal getId() {
        return id;
    }

    public void setId(java.math.BigDecimal id) {
        this.id = id;
    }

    public java.math.BigInteger getPointsDeducted() {
        return pointsDeducted;
    }

    public void setPointsDeducted(java.math.BigInteger pointsDeducted) {
        this.pointsDeducted = pointsDeducted;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public java.math.BigInteger getDays() {
        return days;
    }

    public void setDays(java.math.BigInteger days) {
        this.days = days;
    }

    public java.math.BigInteger getSeen() {
        return seen;
    }

    public void setSeen(java.math.BigInteger seen) {
        this.seen = seen;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    public UserEntity getToUser() {
        return toUser;
    }

    public void setToUser(UserEntity toUser) {
        this.toUser = toUser;
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
        if (!(object instanceof NotificationEntity)) {
            return false;
        }
        NotificationEntity other = (NotificationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.NotificationEntity[ id=" + id + " ]";
    }

}
