package services.notification;

import datagateway.notification.NotificationManager;

import java.time.Duration;

public class NotificationRemover {

    private final NotificationManager notificationManager;
    private final NotificationTracker notificationTracker;

    public NotificationRemover(NotificationManager notificationManager, NotificationTracker notificationTracker) {
        this.notificationManager = notificationManager;
        this.notificationTracker = notificationTracker;
    }

    /**
     * Deletes a notification
     * @param associatedId the id of the associated object that the notification is for
     * @param timeInAdvance the duration in advance that the notification is to be sent out
     */
    public void deleteNotification(Long associatedId, Duration timeInAdvance) {
        notificationManager.deleteNotification(associatedId, timeInAdvance);
        notificationTracker.updateUpcomingNotification();
    }

    /**
     * Deletes all notifications with associatedId
     * @param associatedId the id of the associated object that the notification is for
     */
    public void deleteNotification(Long associatedId) {
        notificationManager.deleteNotification(associatedId);
        notificationTracker.updateUpcomingNotification();
    }
}
