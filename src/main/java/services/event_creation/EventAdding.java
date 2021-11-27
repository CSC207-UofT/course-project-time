package services.event_creation;

public interface EventAdding extends CalendarEventCreationBoundary {
    long addEvent(CalendarEventModel eventData);
    void markEventAsCompleted(long eventId);
}
