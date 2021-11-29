package services.event_creation;

public interface CalendarEventCreationBoundary {

    long addEvent(CalendarEventModel eventData);

    void markEventAsCompleted(long eventId);
}
