package main.java.use_case.notification;

import java.time.Duration;

public class EventNotificationCreationBoundary extends NotificationCreationBoundary {
    private final int eventId;
    private final Duration notificationDurationInAdvance;

    public EventNotificationCreationBoundary(int eventId, Duration notificationTimeInAdvance) {
        this.eventId = eventId;
        this.notificationDurationInAdvance = notificationTimeInAdvance;
    }

    public int getEventId() {
        return eventId;
    }

    public Duration getNotificationDurationInAdvance() {
        return notificationDurationInAdvance;
    }
}
