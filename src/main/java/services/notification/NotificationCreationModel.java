package services.notification;

import java.time.Duration;
import java.time.LocalDateTime;

public interface NotificationCreationModel {
    long getAssociatedId();
    Duration getTimeInAdvance();
    LocalDateTime getNotificationDateTime();
}
