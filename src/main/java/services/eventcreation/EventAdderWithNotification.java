package services.eventcreation;

import services.notification.NotificationAdder;

public class EventAdderWithNotification implements CalendarEventCreationBoundary {

    private final CalendarEventCreationBoundary service;

    public EventAdderWithNotification(CalendarEventCreationBoundary service) {
        this.service = service;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {
        return service.addEvent(eventData);
    }

    @Override
    public long addEvent(EventFromTaskModel eventData) {
        return service.addEvent(eventData);
    }
}
