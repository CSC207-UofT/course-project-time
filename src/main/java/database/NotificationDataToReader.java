package database;

import datagateway.notification.NotificationReader;
import entity.Notification;

import java.time.Duration;
import java.time.LocalDateTime;

public class NotificationDataToReader implements NotificationReader {

    private final NotificationDataClass notification;

    public NotificationDataToReader(NotificationDataClass notification) {
        this.notification = notification;
    }

    @Override
    public long getAssociatedId() {
        return notification.getAssociatedId();
    }

    @Override
    public LocalDateTime getNotificationDateTime() {
        return notification.getNotificationDateTime();
    }

    @Override
    public Duration getTimeInAdvance() {
        return notification.getNotificationTimeInAdvance();
    }

    @Override
    public String getMessage() {
        return notification.getMessage();
    }
}
