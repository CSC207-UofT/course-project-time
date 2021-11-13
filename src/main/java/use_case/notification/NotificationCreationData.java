package main.java.use_case.notification;

import main.java.constants.NotificationType;

import java.time.Duration;

public class NotificationCreationData implements NotificationCreationDTO {
    private final NotificationType notificationType;
    private final int idOfAssociatedObject;
    private final Duration notificationDurationInAdvance;

    public NotificationCreationData(NotificationType notificationType, int idOfAssociatedObject, Duration notificationDurationInAdvance) {
        this.notificationType = notificationType;
        this.idOfAssociatedObject = idOfAssociatedObject;
        this.notificationDurationInAdvance = notificationDurationInAdvance;
    }

    public NotificationCreationData(int idOfAssociatedObject, Duration notificationDurationInAdvance) {
        this.notificationType = null;
        this.idOfAssociatedObject = idOfAssociatedObject;
        this.notificationDurationInAdvance = notificationDurationInAdvance;
    }

    @Override
    public NotificationType getType() {
        return notificationType;
    }

    @Override
    public int getIdOfAssociatedObject() {
        return idOfAssociatedObject;
    }

    @Override
    public Duration getNotificationDurationInAdvance() {
        return notificationDurationInAdvance;
    }
}
