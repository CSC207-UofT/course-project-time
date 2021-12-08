package datagateway.notification;

import entity.Notification;
import services.notification.NotificationCreationModel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public interface NotificationManager {
    void addNotification(Notification notification);

    void deleteNotification(long associatedId, Duration timeInAdvance);

    void deleteNotification(long associatedId);

    List<NotificationReader> getAllNotifications();

    List<NotificationReader> getNotificationsForAssociatedObject(long associatedId);

    NotificationReader getNotification(long associatedId, Duration timeInAdvance);

    void loadNotifications(String filePath) throws IOException;

    void saveNotifications(String filePath) throws IOException;
}
