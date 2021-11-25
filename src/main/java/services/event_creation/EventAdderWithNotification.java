package services.event_creation;

import data_gateway.CalendarManager;
import services.notification_system.NotificationAdder;

public class EventAdderWithNotification implements EventAdding{

    private final CalendarManager calendarManager;
    private final NotificationAdder notificationAdder;

    public EventAdderWithNotification(CalendarManager calendarManager, NotificationAdder notificationAdder) {
        this.calendarManager = calendarManager;
        this.notificationAdder = notificationAdder;
    }

    @Override
    public void addEvent(CalendarEventModel eventData) {
        long eventId = calendarManager.addEvent(eventData);
        notificationAdder.createNotification(eventData, eventId);
    }

    @Override
    public void markEventAsCompleted(long eventId) {
        calendarManager.markEventAsCompleted(eventId);
    }
}
