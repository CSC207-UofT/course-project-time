package main.java.entity_gateway;

import main.java.entity.Notification;

import java.time.LocalDateTime;

public class NotifToNotifReader implements NotificationReader {
    private final int id;
    private final int idOfAssociatedObject;
    private final LocalDateTime notifDateTime;

    public NotifToNotifReader(int id, int idOfAssociatedObject, LocalDateTime notifDateTime) {
        this.id = id;
        this.idOfAssociatedObject = idOfAssociatedObject;
        this.notifDateTime = notifDateTime;
    }

    public NotifToNotifReader(Notification notification) {
        this(notification.getId(), notification.getIfOfAssociatedObject(), notification.getNotifDateTime());
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public LocalDateTime getNotifTime() {
        return this.notifDateTime;
    }

    @Override
    public int getIdOfAssociatedObject() {
        return this.idOfAssociatedObject;
    }
}
