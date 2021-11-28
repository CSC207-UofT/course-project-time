package entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class Notification {
    private final long associatedId;
    private final Duration notificationTimeInAdvance;
    private final LocalDateTime notificationDateTime;
    private final String message;

    public Notification(Long associatedId, Duration notificationTimeInAdvance,
                        LocalDateTime notificationDateTime, String message) {
        this.associatedId = associatedId;
        this.notificationTimeInAdvance = notificationTimeInAdvance;
        this.notificationDateTime = notificationDateTime;
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

    public Duration getNotificationTimeInAdvance() {
        return notificationTimeInAdvance;
    }
}
