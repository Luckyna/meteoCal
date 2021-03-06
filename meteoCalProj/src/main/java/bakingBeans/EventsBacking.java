package bakingBeans;

import EJB.interfaces.CalendarManager;
import EJB.interfaces.EventManager;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import model.Event;
import utility.EventType;

/**
 *
 * @author Francesco
 */
@Named(value = "events")
@ViewScoped
public class EventsBacking implements Serializable {

    @Inject
    CalendarManager calendarManager;

    @Inject
    EventManager eventManager;

    List<Event> ownedEvents;
    List<Event> joinedEvents;
    List<Event> wallEvents;
    List<Event> invitations;

    final LoginBacking login;

    /**
     *
     * GETTERS & SETTERS
     *
     */
    public List<Event> getJoinedEvents() {
        return joinedEvents;
    }

    public void setJoinedEvents(List<Event> joinedEvents) {
        this.joinedEvents = joinedEvents;
    }

    public List<Event> getWallEvents() {
        return wallEvents;
    }

    public void setWallEvents(List<Event> wallEvents) {
        this.wallEvents = wallEvents;
    }

    public List<Event> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Event> invitations) {
        this.invitations = invitations;
    }

    public List<Event> getOwnedEvents() {
        return ownedEvents;
    }

    public void setOwnedEvents(List<Event> ownedEvents) {
        this.ownedEvents = ownedEvents;
    }

    /**
     *
     * METHODS
     *
     */
    /**
     * Creates a new instance of EventsBacking
     */
    public EventsBacking() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //mi salvo il login per ottenere l'info di chi è loggato
        //e crea o modifica l evento
        login = (LoginBacking) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{login}", LoginBacking.class);

    }

    @PostConstruct
    public void init() {
        //riempio le tab
        ownedEvents = eventManager.eventOnWall(EventType.OWNED, login.getCurrentUser());
        joinedEvents = eventManager.eventOnWall(EventType.JOINED, login.getCurrentUser());
        invitations = eventManager.eventOnWall(EventType.INVITED, login.getCurrentUser());
        wallEvents = eventManager.eventOnWall(EventType.PUBLIC, login.getCurrentUser());
    }
}
