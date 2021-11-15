package main.java.services.event_creation;

public interface CalendarEventCreationBoundary {

    boolean addEvent(CalendarEventModel eventData);

    boolean completeEvent(String eventName);
}
