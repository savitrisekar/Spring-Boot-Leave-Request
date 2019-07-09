/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrodatamii.metrodatamii.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Sekar Ayu Safitri
 */
@Entity
@Table(name = "public_holiday")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublicHoliday.findAll", query = "SELECT p FROM PublicHoliday p")
    , @NamedQuery(name = "PublicHoliday.findById", query = "SELECT p FROM PublicHoliday p WHERE p.id = :id")
    , @NamedQuery(name = "PublicHoliday.findByEvent", query = "SELECT p FROM PublicHoliday p WHERE p.event = :event")
    , @NamedQuery(name = "PublicHoliday.findByDate", query = "SELECT p FROM PublicHoliday p WHERE p.date = :date")})
public class PublicHoliday implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "event")
    private String event;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public PublicHoliday() {
    }

    public PublicHoliday(Integer id) {
        this.id = id;
    }

    public PublicHoliday(Integer id, String event, Date date) {
        this.id = id;
        this.event = event;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof PublicHoliday)) {
            return false;
        }
        PublicHoliday other = (PublicHoliday) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metrodatamii.metrodatamii.entities.PublicHoliday[ id=" + id + " ]";
    }
    
}
