package data_gateway.notification;

import entity.Notification;

import java.time.Duration;
import java.time.LocalDateTime;

public class NotificationToNotificationReader implements NotificationReader{

    private final Notification notification;

    public NotificationToNotificationReader(Notification notification) {
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
