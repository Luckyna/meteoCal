/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.MessageFormat;
import java.util.logging.Logger;
import utility.LoggerLevel;
import utility.LoggerProducer;

/**
 *
 * @author Luckyna
 */
public enum NotificationType {

    /**
     * SE AGGIUNGETE ENTITA' A QUESTA CLASSE, DOVETE AGGIUNGERLA ANCHE ALL'ENUM
     * DEL DATABASE CHE TROVATE NELL'ENTITA' NOTIFICATION NEL CAMPO TYPE
     */
    INVITATION("Invitation to event {0}",
            "Hello {0},\nyou''ve received an invitation for event {1} from {2}.\n\n "
            + "Have a look to the event page on the following link:\n\n{3}\n\nMeteoCalendar Team"),

    SIGN_UP_OK("Successful sign up for MeteoCal!",
            "Hello {0},\n we inform you that you successful signed up for MeteoCal, "
            + "login to get access to the best calendar ever!\n\nMeteoCalendar Team"),

    WEATHER_CHANGED("Weather changed for event {0}",
            "Hello {0},\n we inform you that the weather for"
            + "the event {1} has changed.\n\n Check it at:\n\nLink:{3}\n\nMeteoCalendar Team"),
    BAD_WEATHER_TOMORROW("Bad weather forecast for tomorrow event {0}",
            "Hello {0},\nwe are sorry to inform you that the weather forecast for "
            + "the tomorrow event {1} is bad.\n\nLink:{3}\n\nMeteoCalendar Team"),
    BAD_WEATHER_IN_THREE_DAYS("Bad weather forecast for event {0}",
            "Hello {0},\nwe are sorry to inform you that the weather forecast for"
            + "the event {1},scheduled in 3 days, is bad.\n If you want you can reschedule your event. Go to the following link and hit ''Try Reschedule''\n\nLink:{3}\n\nMeteoCalendar Team"),
    EVENT_CHANGED("Event {0} has been modified",
            "Hello {0},\n we inform you that event {1} has been modified\n\nLink:{3}\n\nMeteoCalendar Team"),
    EVENT_CANCELLED("Event {0} has been cancelled",
            "Hello {0},\n we inform you that event {1} has been cancelled\n\nMeteoCalendarTeam"),
    EVENT_CHANGED_TO_PUBLIC(
            "Event {0} has changed privacy from Private to Public",
            "Hello {0},\n we inform you that event {1} has changed its privacy from Private to Public.\n\nLink:{3}\n\nMeteoCalendar Team"),
    EVENT_CHANGED_TO_PRIVATE(
            "Event {0} has changed privacy from Public to Private",
            "Hello {0},\n we inform you that event {1} has changed its privacy from Public to Private.\n\nMeteoCalendar Team");

    private String subject = "";
    private String bodyMessage = "";
    private String eventOwner = "";
    private String inviteeName = "";
    private String eventName = "";
    private String link = "";

    //template
    private final String templateSubject;
    private final String templateBodyMessage;

    private final String EVENT_LINK = "localhost:8080/meteoCalProj/s/eventPage.xhtml?id=";
    private final String RESCHEDULE_LINK = "localhost:8080/meteoCalProj/s/manageEvent.xhtml?id=";

    private Object[] subjParams;
    private Object[] bodyParams;

    private final Logger logger = LoggerProducer.debugLogger(NotificationType.class);

    private NotificationType(String subject, String body) {
        this.templateSubject = subject;
        this.templateBodyMessage = body;
    }

    public NotificationType setEventOwner(String eventOwner) {
        this.eventOwner = eventOwner;
        return this;
    }

    public NotificationType setInviteeName(String inviteeName) {
        this.inviteeName = inviteeName;
        return this;
    }

    public NotificationType setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public NotificationType setLink(Long id) {
        if (this == NotificationType.BAD_WEATHER_IN_THREE_DAYS) {
            //se la notifica riguarda il brutto tempo in 3 gionri
            //mando il link che propone la reschedule al volo
            this.link = RESCHEDULE_LINK + id; 
        } else {
            //altrimenti il link dell'evento
            this.link = EVENT_LINK + id;
        }
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public String getBodyMessage() {
        return bodyMessage;
    }

    public NotificationType buildEmail() {
        subjParams = new Object[]{eventName};
        bodyParams = new Object[]{inviteeName, eventName, eventOwner, link};

        subject = MessageFormat.format(templateSubject, subjParams);
        bodyMessage = MessageFormat.format(templateBodyMessage, bodyParams);
        logger.log(LoggerLevel.DEBUG,
                "Email formattata:\nsubject: " + subject + "\nbody: "
                + bodyMessage);
        return this;
    }

}
