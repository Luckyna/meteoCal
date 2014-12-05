/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Luckyna
 */
@Entity
public abstract class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Calendar startDateTime ;
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Calendar endDateTime;
    private String location;
    private String description;
    private boolean isOutdoor;
    private Long owner;
    
    @ManyToMany
    @JoinTable(name = "Invitation", joinColumns = @JoinColumn(name = "event"), inverseJoinColumns = @JoinColumn(name = "user"))
    private List<User> invitee;
    
    @ManyToMany(mappedBy = "events")
    private List<Calendar> inCalendars;
    
    
    //METHODS

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public Calendar getStartDateTime() {
        return startDateTime;
    }

    public Calendar getEndDateTime() {
        return endDateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsOutdoor() {
        return isOutdoor;
    }

    public Long getOwner() {
        return owner;
    }

    public List<User> getInvitee() {
        return invitee;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Event[ id=" + id + " ]";
    }
    
}