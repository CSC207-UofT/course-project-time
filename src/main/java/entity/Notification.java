package main.java.entity;

import java.time.LocalDateTime;

public abstract class Notification {

    private Type type;
    private int associatedId;
    private LocalDateTime notificationTime;

    public Notification(Type type, int associatedId, LocalDateTime notificationTime) {
        this.type = type;
        this.associatedId = associatedId;
        this.notificationTime = notificationTime;
    }

    public int getAssociatedId() {
        return associatedId;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }
}
