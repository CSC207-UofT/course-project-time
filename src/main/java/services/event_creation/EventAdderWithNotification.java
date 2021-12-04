package services.event_creation;

import data_gateway.event.CalendarManager;
import services.notification.NotificationAdder;

public class EventAdderWithNotification implements CalendarEventCreationBoundary {

    private final CalendarEventCreationBoundary service;
    private final NotificationAdder notificationAdder;

    public EventAdderWithNotification(CalendarEventCreationBoundary service, NotificationAdder notificationAdder) {
        this.service = service;
        this.notificationAdder = notificationAdder;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {
        long eventId = service.addEvent(eventData);
        notificationAdder.createNotification(eventData, eventId);
        return eventId;
    }

    @Override
    public void markEventAsCompleted(long eventId) {
        service.markEventAsCompleted(eventId);
    }
}
