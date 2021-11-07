package main.java.entity;

import main.java.constants.NotificationType;

import java.time.LocalDateTime;

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
