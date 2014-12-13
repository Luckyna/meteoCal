package bakingBeans;

import EJB.interfaces.CalendarManager;
import EJB.interfaces.EventManager;
import EJB.interfaces.InvitationManager;
import EJB.interfaces.SearchManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.CalendarModel;
import model.Event;
import model.PrivateEvent;
import model.PublicEvent;
import model.UserModel;

/**
 *
 * @author Francesco
 */
@Named(value = "newEvent")
@ViewScoped
public class ManageEventBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{newEvent.idEvent}")
    String idEvent;

    Event eventToCreate;

    String description = "description";
    String location = "location";
    boolean outdoor;
    boolean publicAccess;
    String title = "initialTitle";
    String startDate = "10/12/2015";
    String endDate = "12/12/2015";
    String startTime = "01:03";
    String endTime = "05:07";
    String calendarName;
    String newGuestEmail = "invita qualcuno";
    List<UserModel> resultUsers;
    boolean displayResultUsers;
//
//    @ManagedProperty(value="#{param.emailToInvite}")
//    private String emailToInvite;

    boolean saved;

    CalendarModel calendar;

    List<UserModel> guests = new ArrayList<>();

    LoginBacking login;

    java.util.Calendar startDateTime;
    java.util.Calendar endDateTime;

    @Inject
    CalendarManager calendarManager;

    @Inject
    EventManager eventManager;

    @Inject
    InvitationManager invitationManager;

    @Inject
    SearchManager searchManager;

    private UserModel newGuest;

    public ManageEventBacking() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //mi salvo il login per ottenere l'info di chi è loggato
        //e crea o modifica l evento
        login = (LoginBacking) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{login}", LoginBacking.class);
    }

    /*
     *
     * GETTERS & SETTERS
     *
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String date) {
        this.startDate = date;
    }

    public void setEndDate(String date) {
        this.endDate = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOutdoor(boolean isOutdoor) {
        this.outdoor = isOutdoor;
    }

    public void setPublicAccess(boolean isPublic) {
        this.publicAccess = isPublic;
    }

    public void setStartTime(String time) {
        this.startTime = time;
    }

    public void setEndTime(String time) {
        this.endTime = time;
    }

    public List<UserModel> getGuests() {
        return guests;
    }

    public void setGuests(List<UserModel> guests) {
        this.guests = guests;
    }

    public List<UserModel> getResultUsers() {
        return resultUsers;
    }

    public void setResultUsers(List<UserModel> resultUsers) {
        this.resultUsers = resultUsers;
    }

    public boolean isDisplayResultUsers() {
        return displayResultUsers;
    }

    public void setDisplayResultUsers(boolean displayResultUsers) {
        this.displayResultUsers = displayResultUsers;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setCalendarName(String nameCal) {
        this.calendarName = nameCal;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public boolean isPublicAccess() {
        return publicAccess;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getNewGuestEmail() {
        return newGuestEmail;
    }

    public void setNewGuestEmail(String newGuestEmail) {
        this.newGuestEmail = newGuestEmail;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    /*
     *
     * METHODS
     *
     */
    /**
     * Creates a new instance of newEvent
     */
    @PostConstruct
    public void setEditModality() {
        //riempire campi title,location etc con
        //quelli dell evento con id specificato
        //nel param
        //setSaved(true);
    }

    public void setInCalendar(String calendarName) {
        List<CalendarModel> calendars = calendarManager.getCalendars(login.getCurrentUser());
        if (calendarName != null) {
            //salvo in calendar l istanza di Calendar che appartiene all utente
            //e che ha il title specificato nel form
            calendar = findCalendarByName(calendars, calendarName);
        }
    }

    private CalendarModel findCalendarByName(List<CalendarModel> calendars, String name) {
        for (CalendarModel cal : calendars) {
            if (cal.getTitle().equals(name)) {
                return cal;
            }
        }
        return null;
    }

    public void save() {

        createOrLoadInstance();
        setUpInstance();
        saveIt();

    }

    public String delete() {
        eventManager.DeleteEvent(eventToCreate);
        return "/s/myCalendar.xhtml";
    }

    public void invite(String emailToInvite) {
        if (emailToInvite != null) {
            UserModel userToInvite = findGuest(resultUsers, emailToInvite);
            System.out.println("--resultUsers è " + resultUsers);
            System.out.println("--emailToInvite è " + emailToInvite);

            if (userToInvite != null) {
                System.out.println("--userToInvite è" + userToInvite);
                if (!guests.contains(userToInvite)) {
                    guests.add(userToInvite);
                } else {
                    //msg c'è già
                }
            } else {
                System.out.println("-fallita findGuest");
            }
            displayResultUsers = false;
        } else {
            System.out.println("--emailToInvite è null");
        }
    }

    public void showResultUsers() {
        System.out.println("-newGuestEmail" + newGuestEmail);
        resultUsers = searchManager.searchUsers(newGuestEmail);
        System.out.println("-newGuestEmail" + newGuestEmail);
        System.out.println("-resultUsers" + resultUsers);
        if (resultUsers != null && resultUsers.size() > 0) {
            displayResultUsers = true;
        } else {
            //todo msg errore
        }
    }

    /**
     * se l'evento è già stato creato ne carica l'istanza altrimenti ne crea uno
     * nuovo, sempre in eventToCreate
     */
    private void createOrLoadInstance() {
        if (isSaved()) {
            //eventToCreate = eventManager.findEventById(idEvent);
        } else {
            //se l'utente ha impostato a public l evento
            if (publicAccess) {
                //istanzio un PublicEvent
                eventToCreate = new PublicEvent();
            } else {
                //PrivateEvent altrimenti
                eventToCreate = new PrivateEvent();
            }
        }
    }

    /**
     * imposta tutti i parametri di eventToCreate con i campi del form impostati
     * dall'utente
     */
    private void setUpInstance() {
        //creo un Calendar per l'inizio
        startDateTime = Calendar.getInstance();
        String[] startDateToken = startDate.split("-");
        String[] startTimeToken = startTime.split(":");
        //lo setto all'anno, al mese (contato da zero), giorno e ora, min e secondi
        startDateTime.set(Integer.parseInt(startDateToken[0]),
                Integer.parseInt(startDateToken[1]) - 1,
                Integer.parseInt(startDateToken[2]),
                Integer.parseInt(startTimeToken[0]),
                Integer.parseInt(startTimeToken[1]), 0);

        //creo un Calendar per la fine
        endDateTime = Calendar.getInstance();
        String[] endDateToken = endDate.split("-");
        String[] endTimeToken = endTime.split(":");
        //lo setto all'anno, al mese (contato da zero), giorno e ora, min e secondi
        endDateTime.set(Integer.parseInt(endDateToken[0]),
                Integer.parseInt(endDateToken[1]) - 1,
                Integer.parseInt(endDateToken[2]),
                Integer.parseInt(endTimeToken[0]),
                Integer.parseInt(endTimeToken[1]), 0);

        //riempio un entità di Event con i vari attributi
        eventToCreate.setDescription(description);
        eventToCreate.setTitle(title);
        eventToCreate.setLocation(location);
        eventToCreate.setIsOutdoor(outdoor);
        eventToCreate.setStartDateTime(startDateTime);
        eventToCreate.setEndDateTime(endDateTime);
        eventToCreate.setOwner(login.getCurrentUser());

        //setto calendar all'entità corrispondente al calendarName
        setInCalendar(calendarName);
    }

    /**
     * fa persistere l'evento, aggiungere al calendario e creare gli inviti
     * all'eventManager
     */
    private void saveIt() {
        //passo all eventManager l'ownerId, l'evento riempito, il calendario
        //dove metterlo e la lista degli invitati
        if (eventManager.scheduleNewEvent(eventToCreate, calendar, guests)) {
            setSaved(true);
            idEvent = eventToCreate.getId().toString();
        }
    }

    private UserModel findGuest(List<UserModel> users, String email) {
        if (users != null) {
            for (UserModel u : users) {
                if (u.getEmail().equals(email)) {
                    return u;
                }
            }
        }
        return null;
    }

}
