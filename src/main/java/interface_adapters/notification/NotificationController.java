package main.java.interface_adapters.notification;

import main.java.constants.NotificationType;
import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.TodoListManager;
import main.java.use_case.notification.*;

import java.time.Duration;

public class NotificationController {
    private final NotificationAdder notificationAdder;
    private final NotificationRemover notificationRemover;
    private final NotificationTracker notificationTracker;
    private NotificationPresenter notificationPresenter;

    public NotificationController(NotificationTracker notificationTracker,
                                  CalendarManager calendarManager,
                                  TodoListManager todoListManager,
                                  NotificationPresenter notificationPresenter) {
        this.notificationAdder = new NotificationAdder(notificationTracker, calendarManager, todoListManager);
        this.notificationRemover = new NotificationRemover(notificationTracker, calendarManager, todoListManager);
        this.notificationTracker = notificationTracker;
        this.notificationPresenter = notificationPresenter;
    }

    /**
     * Starts tracking if there are notifications to be sent.
     * Should only be called once at runtime.
     */
    public synchronized void startTrackingNotifications() {
        Thread thread = new Thread(this.notificationTracker);
        thread.start();
    }

    /**
     * Goes through all events, tasks, etc to find existing notifications. Add these notifications
     * to the NotificationTracker. Should only be called once at runtime.
     * @return whether the notifications are populated successfully
     */
    public boolean populateTrackerWithNotifications() {
        return this.notificationAdder.populateTrackerWithNotifications();
    }

    /**
     * Creates a notification.
     * @param notificationType      type of notification e.g. event, task
     * @param idOfAssociatedObject  id of the type that the notification is for e.g. event, task
     * @param notifDurationInAdvance    how long in advance this notification should be sent out
     * @return whether the notification has been created successfully
     */
    public boolean createNotifications(NotificationType notificationType, int idOfAssociatedObject, Duration notifDurationInAdvance) {
        NotificationCreationBoundary boundary = new NotificationCreationBoundary(notificationType, idOfAssociatedObject, notifDurationInAdvance);
        return this.notificationAdder.createNotifications(boundary);
    }

    /**
     * Delete a notification.
     * @param idOfAssociatedObject      id of the associated object whose notifications are to be deleted
     * @param notifDurationInAdvance    duration in advance that the notification should be sent
     * @return whether the notification has been deleted successfully
     */
    public boolean deleteNotification(int idOfAssociatedObject, Duration notifDurationInAdvance) {
        NotificationCreationBoundary boundary = new NotificationCreationBoundary(idOfAssociatedObject, notifDurationInAdvance);
        return this.notificationRemover.deleteNotification(boundary);
    }

    /**
     * Delete all notifications associated with the object, given the object's id.
     * The object could be an event, task, etc.
     * @param idOfAssociatedObject      id of the associated object whose notifications are to be deleted
     * @return whether the notifications have been deleted successfully
     */
    public boolean deleteNotifications(int idOfAssociatedObject) {
        return this.notificationRemover.deleteNotifications(idOfAssociatedObject);
    }

    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        this.notificationPresenter.resetNotification(notificationSettings);
    }

}
