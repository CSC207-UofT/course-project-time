package services.event_creation;

public interface CalendarEventCreationBoundary {

    void addEvent(CalendarEventModel eventData);

    void markEventAsCompleted(long eventId);
}
