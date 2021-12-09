package services.eventcreation;

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
        return service.addEvent(eventData);
    }

    @Override
    public long addEvent(EventFromTaskModel eventData) {
        return service.addEvent(eventData);
    }
}
