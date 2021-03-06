/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Luckyna
 */
@Entity
@IdClass(InvitationId.class)
@NamedQueries({
    @NamedQuery(name = "findInvitedEvents", query = "SELECT i.event FROM Invitation i WHERE i.invitee = :user AND i.event.endDateTime > CURRENT_TIMESTAMP ORDER BY i.event.startDateTime ASC"),
     @NamedQuery(name = "findAcceptedInvitations", query = "SELECT i.event FROM Invitation i WHERE i.invitee = :user AND i.answer=model.InvitationAnswer.YES AND i.event.endDateTime > CURRENT_TIMESTAMP ORDER BY i.event.startDateTime ASC"),
    
    @NamedQuery(name = "findInvitation", query = "SELECT i FROM Invitation i WHERE i.event=:event AND i.invitee=:user")
})

public class Invitation implements Serializable {

    @Id
    @ManyToOne
    private UserModel invitee;

    @Id
    @ManyToOne(targetEntity = Event.class)
    @JoinColumn
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('YES', 'NO', 'NA')")
    private InvitationAnswer answer;

    /*
     *
     *   CONSTRUCTORS
     */
    public Invitation(UserModel invitee, Event event) {
        this.invitee = invitee;
        this.event = event;
        this.answer = InvitationAnswer.NA;

    }

    public Invitation() {
    }

    /*
     *
     *   CONSTRUCTORS
     */
    public UserModel getInvitee() {
        return invitee;
    }

    public void setInvitee(UserModel invitee) {
        this.invitee = invitee;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public InvitationAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(InvitationAnswer answer) {
        this.answer = answer;
    }

}
