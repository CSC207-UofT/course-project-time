package services.eventcreation;

public interface CalendarEventCreationBoundary {

    long addEvent(CalendarEventModel eventData);

    long addEvent(EventFromTaskModel eventData);

}
