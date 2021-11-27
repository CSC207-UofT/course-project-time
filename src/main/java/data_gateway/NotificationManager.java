package data_gateway;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationManager {
    void addNotification(long associatedId, LocalDateTime NotificationDateTime, String message);
    List<NotificationReader> getAllNotifications();
    void loadNotifications(String filePath) throws IOException;
    void saveNotifications(String filePath) throws IOException;
}
