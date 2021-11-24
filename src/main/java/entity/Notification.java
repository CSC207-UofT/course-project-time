package entity;

import java.time.LocalDateTime;

public class Notification {
    private final long associatedId;
    private final LocalDateTime notificationDateTime;
    private final String message;

    public Notification(LocalDateTime dateTime, int associatedId, String message) {
        this.notificationDateTime = dateTime;
        this.associatedId = associatedId;
        this.message = message;
    }

    public long getAssociatedId() {
        return associatedId;
    }

    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }

    public String getMessage() {
        return message;
    }
}
