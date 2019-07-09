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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Sekar Ayu Safitri
 */
@Entity
@Table(name = "leave_request_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveRequestStatus.findAll", query = "SELECT l FROM LeaveRequestStatus l")
    , @NamedQuery(name = "LeaveRequestStatus.findById", query = "SELECT l FROM LeaveRequestStatus l WHERE l.id = :id")
    , @NamedQuery(name = "LeaveRequestStatus.findByLeaveRequest", query = "SELECT l FROM LeaveRequestStatus l WHERE l.leaveRequest = :leaveRequest")
    , @NamedQuery(name = "LeaveRequestStatus.findByStatusDate", query = "SELECT l FROM LeaveRequestStatus l WHERE l.statusDate = :statusDate")
    , @NamedQuery(name = "LeaveRequestStatus.findByStatusNotes", query = "SELECT l FROM LeaveRequestStatus l WHERE l.statusNotes = :statusNotes")})
public class LeaveRequestStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "leave_request")
    private String leaveRequest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_date")
    @Temporal(TemporalType.DATE)
    private Date statusDate;
    @Size(max = 999)
    @Column(name = "status_notes")
    private String statusNotes;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Status status;

    public LeaveRequestStatus() {
    }

    public LeaveRequestStatus(Integer id) {
        this.id = id;
    }

    public LeaveRequestStatus(Integer id, String leaveRequest, Date statusDate) {
        this.id = id;
        this.leaveRequest = leaveRequest;
        this.statusDate = statusDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(String leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatusNotes() {
        return statusNotes;
    }

    public void setStatusNotes(String statusNotes) {
        this.statusNotes = statusNotes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (!(object instanceof LeaveRequestStatus)) {
            return false;
        }
        LeaveRequestStatus other = (LeaveRequestStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "metrodatamii.metrodatamii.entities.LeaveRequestStatus[ id=" + id + " ]";
    }
    
}
