package services.eventcreation;

public interface EventWithNotificationCreationBoundary extends CalendarEventCreationBoundary{
    long addEvent(EventWithNotificationModel eventData);
}
