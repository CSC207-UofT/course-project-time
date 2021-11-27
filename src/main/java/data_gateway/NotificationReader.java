package data_gateway;

import java.time.LocalDateTime;

public interface NotificationReader {
    long getAssociatedId();
    LocalDateTime getNotificationDateTime();
    String getMessage();
}
