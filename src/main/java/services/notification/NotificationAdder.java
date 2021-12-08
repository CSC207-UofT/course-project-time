package services.notification;

import datagateway.notification.NotificationManager;
import entity.Notification;

import java.time.Duration;
import java.time.LocalDateTime;

public class NotificationAdder {

    private final NotificationManager notificationManager;
    private final NotificationTracker notificationTracker;

    public NotificationAdder(NotificationManager notificationManager, NotificationTracker notificationTracker) {
        this.notificationManager = notificationManager;
        this.notificationTracker = notificationTracker;
    }

    public void addNotification(NotificationCreationModel notificationCreationData) {
        long associatedId = notificationCreationData.getAssociatedId();
        Duration notificationTimeInAdvance = notificationCreationData.getNotificationTimeInAdvance();
        LocalDateTime notificationDateTime = notificationCreationData.getNotificationDateTime();
        String message = notificationCreationData.getMessage();

        Notification notification = new Notification(
                associatedId,
                notificationTimeInAdvance,
                notificationDateTime,
                message
        );

        notificationManager.addNotification(notification);

        notificationTracker.updateUpcomingNotification();
    }
}
