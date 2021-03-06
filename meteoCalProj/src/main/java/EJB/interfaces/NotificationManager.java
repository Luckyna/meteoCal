/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.interfaces;

import java.util.List;
import model.Event;
import model.Notification;
import model.NotificationType;
import model.UserModel;
import utility.NotificationCategories;

/**
 *
 * @author Umberto
 */
public interface NotificationManager {

    /**
     * Creates a notfication of the specified type for the users in the list
     *
     * @param users list of users ot notify
     * @param event
     * @param type
     * @param sendEmail
     *
     */
    public void createNotifications(List<UserModel> users, Event event, NotificationType type, boolean sendEmail);

    public int getUnreadNotificationNumber(UserModel user);

    public List<Notification> getNotificationFiltred(UserModel user, NotificationCategories type);

    public void markAllAsRead(List<Notification> notifications);

    public void sendSignUpConfirmation(UserModel user);
}
