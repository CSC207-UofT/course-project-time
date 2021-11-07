package main.java.interface_adapters.notification;

import main.java.constants.Type;
import main.java.use_case.notification.*;

import java.time.Duration;

public class NotificationController {
    private final NotificationAdder notificationAdder;
    private final NotificationRemover notificationRemover;
    private final NotificationTracker notificationTracker;

    public NotificationController(NotificationTracker notificationTracker) {
        this.notificationAdder = new NotificationAdder(notificationTracker);
        this.notificationRemover = new NotificationRemover(notificationTracker);
        this.notificationTracker = notificationTracker;
    }

    /**
     * Starts tracking if there are notifications to be sent.
     * Should only be called once at runtime.
     */
    public void startTrackingNotifications() {
        Thread thread = new Thread(this.notificationTracker);
        thread.start();
    }

    /**
     * Goes through all events, tasks, etc to find existing notifications. Add these notifications
     * to the NotificationTracker. Should only be called once at runtime.
     */
    public void populateTrackerWithNotifications() {
        this.notificationAdder.populateTrackerWithNotifications();
    }

    public boolean createNotification(Type type, int idOfAssociatedObject, Duration notifDurationInAdvance) {
        NotificationCreationBoundary boundary = new NotificationCreationBoundary(type, idOfAssociatedObject, notifDurationInAdvance);
        this.notificationAdder.createNotification(boundary);
    }

    public boolean deleteNotification(int idOfAssociatedObject, Duration notifDurationInAdvance) {
        NotificationCreationBoundary boundary = new NotificationCreationBoundary(idOfAssociatedObject, notifDurationInAdvance);
        this.notificationAdder.createNotification(boundary);
    }

    public boolean deleteNotifications(int idOfAssociatedObject) {
        return this.notificationRemover.deleteNotifications(idOfAssociatedObject);
    }

}
