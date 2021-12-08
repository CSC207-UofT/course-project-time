package services.notification;

import java.time.Duration;
import java.time.LocalDateTime;

public class NotificationData implements NotificationCreationModel{

    private final long associatedId;
    private final Duration notificationTimeInAdvance;
    private final LocalDateTime notificationDateTime;
    private final String message;

    public NotificationData(long associatedId, Duration notificationTimeInAdvance,
                            LocalDateTime notificationDateTime, String message) {
        this.associatedId = associatedId;
        this.notificationTimeInAdvance = notificationTimeInAdvance;
        this.notificationDateTime = notificationDateTime;
        this.message = message;
    }

    @Override
    public long getAssociatedId() {
        return associatedId;
    }

    @Override
    public Duration getNotificationTimeInAdvance() {
        return notificationTimeInAdvance;
    }

    @Override
    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
