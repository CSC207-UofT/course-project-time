package services.event_creation;

import data_gateway.CalendarManager;
import services.notification_system.NotificationAdder;

import java.time.Duration;

public class EventAdderWithNotification implements CalendarEventCreationBoundary {

    private final EventAdder service;
    private final NotificationAdder notificationAdder;

    public EventAdderWithNotification(EventAdder service, NotificationAdder notificationAdder) {
        this.service = service;
        this.notificationAdder = notificationAdder;
    }

    @Override
    public long addEvent(CalendarEventModel eventData, Duration notificationTimeInAdvance) {
        long eventId = service.addEvent(eventData);
        notificationAdder.createNotification(eventData, eventId, notificationTimeInAdvance);
        return eventId;
    }

    @Override
    public void markEventAsCompleted(long eventId) {
        service.markEventAsCompleted(eventId);
    }
}
