package data_gateway.notification;

import services.notification.NotificationCreationModel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public interface NotificationManager {
    void addNotification(NotificationCreationModel model);

    void deleteNotification(long associatedId, Duration timeInAdvance);

    List<NotificationReader> getAllNotifications();

    List<NotificationReader> getNotificationsForAssociatedObject(long associatedId);

    NotificationReader getNotification(long associatedId, Duration timeInAdvance);

    void loadNotifications(String filePath) throws IOException;

    void saveNotifications(String filePath) throws IOException;
}
