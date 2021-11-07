package main.java.use_case.notification;

import main.java.constants.NotificationType;

import java.time.Duration;

public class NotificationCreationBoundary {
    private final NotificationType notificationType;
    private final int idOfAssociatedObject;
    private final Duration notificationDurationInAdvance;

    public NotificationCreationBoundary(NotificationType notificationType, int idOfAssociatedObject, Duration notificationDurationInAdvance) {
        this.notificationType = notificationType;
        this.idOfAssociatedObject = idOfAssociatedObject;
        this.notificationDurationInAdvance = notificationDurationInAdvance;
    }

    public NotificationCreationBoundary(int idOfAssociatedObject, Duration notificationDurationInAdvance) {
        this.notificationType = null;
        this.idOfAssociatedObject = idOfAssociatedObject;
        this.notificationDurationInAdvance = notificationDurationInAdvance;
    }

    public NotificationType getType() {
        return notificationType;
    }

    public int getIdOfAssociatedObject() {
        return idOfAssociatedObject;
    }

    public Duration getNotificationDurationInAdvance() {
        return notificationDurationInAdvance;
    }
}
