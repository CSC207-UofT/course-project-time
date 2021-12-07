package datagateway.notification;

import java.time.Duration;
import java.time.LocalDateTime;

public interface NotificationReader {
    long getAssociatedId();
    LocalDateTime getNotificationDateTime();
    Duration getTimeInAdvance();
    String getMessage();
}
