package services.event_creation;

public interface EventAdding extends CalendarEventCreationBoundary {
    void addEvent(CalendarEventModel eventData);
    void markEventAsCompleted(long eventId);
}
