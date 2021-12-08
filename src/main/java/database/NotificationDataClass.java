package database;

import java.time.Duration;
import java.time.LocalDateTime;

// Dataclass used by our database. Currently, identical to notification entity, but decoupled to appeal to CA
public class NotificationDataClass {
    private final long associatedId;
    private final Duration notificationTimeInAdvance;
    private final LocalDateTime notificationDateTime;
    private final String message;

    public NotificationDataClass(Long associatedId, Duration notificationTimeInAdvance, LocalDateTime notificationDateTime, String message) {
        this.associatedId = associatedId;
        this.notificationTimeInAdvance = notificationTimeInAdvance;
        this.notificationDateTime = notificationDateTime;
        this.message = message;
    }

    public long getAssociatedId() {
        return associatedId;
    }

    public Duration getNotificationTimeInAdvance() {
        return notificationTimeInAdvance;
    }

    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }

    public String getMessage() {
        return message;
    }
}
