package main.java.use_case.notification;

import java.time.Duration;

public abstract class NotificationCreationBoundary {
    private int idOfAssociatedObject;

    private Duration notificationDurationInAdvance;
}
