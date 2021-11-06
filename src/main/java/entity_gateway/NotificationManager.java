package main.java.entity_gateway;

import main.java.use_case.notification.NotificationCreationBoundary;

import java.util.List;

public interface NotificationManager {
    int addNotification(NotificationCreationBoundary notifBoundary);

    NotificationReader getNotification(int notifId);

    List<NotificationReader> getAllNotifications();

    boolean deleteNotification(int notifId);
}
