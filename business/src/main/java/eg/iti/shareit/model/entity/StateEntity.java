/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yousef
 */
@Entity
@Table(name = "T_STATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StateEntity.findAll", query = "SELECT s FROM StateEntity s"),
    @NamedQuery(name = "StateEntity.findById", query = "SELECT s FROM StateEntity s WHERE s.id = :id"),
    @NamedQuery(name = "StateEntity.findByState", query = "SELECT s FROM StateEntity s WHERE s.state = :state")})
public class StateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATE")
    private String state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    private Collection<AddressEntity> addressEntityCollection;

    public StateEntity() {
    }

    public StateEntity(BigDecimal id) {
        this.id = id;
    }

    public StateEntity(BigDecimal id, String state) {
        this.id = id;
        this.state = state;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlTransient
    public Collection<AddressEntity> getAddressEntityCollection() {
        return addressEntityCollection;
    }

    public void setAddressEntityCollection(Collection<AddressEntity> addressEntityCollection) {
        this.addressEntityCollection = addressEntityCollection;
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
        if (!(object instanceof StateEntity)) {
            return false;
        }
        StateEntity other = (StateEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eg.iti.shareit.model.entity.StateEntity[ id=" + id + " ]";
    }
    
}
