package services.event_creation;

import data_gateway.CalendarManager;
import services.notification_system.NotificationAdder;

public class EventAdderWithNotification implements EventAdding{

    private final EventAdder service;
    private final NotificationAdder notificationAdder;

    public EventAdderWithNotification(EventAdder service, NotificationAdder notificationAdder) {
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
