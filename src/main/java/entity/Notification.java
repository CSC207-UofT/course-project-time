package main.java.entity;

import main.java.constants.NotificationType;

import java.time.LocalDateTime;

/**
 * A Notification stores the information of one-time notification
 * - notificationType: The type of notification, which decide the format of notification message
 * - associatedId: the id related to the notification target, which decides the content of notification
 * - notificationTime: the time to send this notification
 */
public class Notification {

    private final NotificationType notificationType;
    private final int associatedId;
    private final LocalDateTime notificationTime;

    public Notification(NotificationType notificationType, int associatedId, LocalDateTime notificationTime) {
        this.notificationType = notificationType;
        this.associatedId = associatedId;
        this.notificationTime = notificationTime;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public int getAssociatedId() {
        return associatedId;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }
}
