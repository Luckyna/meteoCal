/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 *
 * @author Luckyna
 */
@Entity
@Table(name = "PUBLIC_EVENT")
@NamedQuery(name = "findNextPublicEvents",
            query = "SELECT e FROM PublicEvent e WHERE e.endDateTime>= CURRENT_TIMESTAMP ORDER BY e.startDateTime ASC")
@DiscriminatorValue("PUBLIC")
public class PublicEvent extends Event {

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "PUBLIC_JOIN")
    private List<UserModel> guests;

    /**
     *
     * CONSTRUCTORS
     */
    public PublicEvent(String title, Calendar startDateTime, Calendar endDateTime, String location, String description, boolean isOutdoor, UserModel owner) {
        super(title, startDateTime, endDateTime, location, description,
                isOutdoor, owner);
        guests = new ArrayList<>();
    }

    public PublicEvent() {
        guests = new ArrayList<>();
    }

    /**
     *
     * SETTERS & GETTERS
     */
    public List<UserModel> getGuests() {
        return guests;
    }

    public void setGuests(List<UserModel> guests) {
        this.guests = guests;
    }

    public boolean addGuest(UserModel user) {
        return this.guests.add(user);
    }

    public boolean removeGuest(UserModel user) {
        return this.guests.remove(user);
    }

    @PreRemove
    private void detachRelations() {
        //stacco gli elmenti che non voglio cancellare in cascade
        super.detachNotifications();
        this.guests.clear();        
    }
}
