package main.java.entity;

import main.java.constants.Type;

import java.time.LocalDateTime;

public class Notification {

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
