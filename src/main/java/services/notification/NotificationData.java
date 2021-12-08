package services.notification;

import java.time.Duration;
import java.time.LocalDateTime;

public class NotificationData implements NotificationCreationModel{

    private final long associatedId;
    private final Duration timeInAdvance;
    private final LocalDateTime notificationDateTime;

    public NotificationData(long associatedId, Duration timeInAdvance, LocalDateTime notificationDateTime) {
        this.associatedId = associatedId;
        this.timeInAdvance = timeInAdvance;
        this.notificationDateTime = notificationDateTime;
    }

    @Override
    public long getAssociatedId() {
        return associatedId;
    }

    @Override
    public Duration getTimeInAdvance() {
        return timeInAdvance;
    }

    @Override
    public LocalDateTime getNotificationDateTime() {
        return notificationDateTime;
    }
}
