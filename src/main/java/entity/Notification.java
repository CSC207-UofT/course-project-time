package entity;

import java.time.LocalDateTime;

public class Notification {
    private final long associatedId;
    private final LocalDateTime dateTime;
    private final String message;

    public Notification(LocalDateTime dateTime, int associatedId, String message) {
        this.dateTime = dateTime;
        this.associatedId = associatedId;
        this.message = message;
    }

    public long getAssociatedId() {
        return associatedId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }
}
