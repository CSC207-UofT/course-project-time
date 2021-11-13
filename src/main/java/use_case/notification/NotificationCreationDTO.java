package main.java.use_case.notification;

import main.java.constants.NotificationType;

import java.time.Duration;

public interface NotificationCreationDTO {
    NotificationType getType();

    int getIdOfAssociatedObject();

    Duration getNotificationDurationInAdvance();
}
