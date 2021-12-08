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

    public void deleteNotification(Long associatedId, Duration timeInAdvance) {
        notificationManager.deleteNotification(associatedId, timeInAdvance);
        notificationTracker.updateUpcomingNotification();
    }

    public void deleteNotification(Long associatedId) {
        notificationManager.deleteNotification(associatedId);
        notificationTracker.updateUpcomingNotification();
    }
}
