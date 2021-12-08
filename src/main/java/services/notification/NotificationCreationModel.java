package services.notification;

import java.time.Duration;
import java.time.LocalDateTime;

public interface NotificationCreationModel {
    long getAssociatedId();
    Duration getNotificationTimeInAdvance();
    LocalDateTime getNotificationDateTime();
    String getMessage();
}
