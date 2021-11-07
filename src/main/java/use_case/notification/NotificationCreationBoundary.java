package main.java.use_case.notification;

import main.java.constants.Type;

import java.time.Duration;

public class NotificationCreationBoundary {
    private final Type type;
    private final int idOfAssociatedObject;
    private final Duration notificationDurationInAdvance;

    public NotificationCreationBoundary(Type type, int idOfAssociatedObject, Duration notificationDurationInAdvance) {
        this.type = type;
        this.idOfAssociatedObject = idOfAssociatedObject;
        this.notificationDurationInAdvance = notificationDurationInAdvance;
    }

    public NotificationCreationBoundary(int idOfAssociatedObject, Duration notificationDurationInAdvance) {
        this.type = null;
        this.idOfAssociatedObject = idOfAssociatedObject;
        this.notificationDurationInAdvance = notificationDurationInAdvance;
    }

    public Type getType() {
        return type;
    }

    public int getIdOfAssociatedObject() {
        return idOfAssociatedObject;
    }

    public Duration getNotificationDurationInAdvance() {
        return notificationDurationInAdvance;
    }
}
