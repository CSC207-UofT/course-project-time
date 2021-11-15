package main.java.services.event_creation;

import main.java.data_gateway.CalendarManager;

public class EventAdder implements CalendarEventCreationBoundary {

    private final CalendarManager calendarManager;

    public EventAdder(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public boolean addEvent(CalendarEventModel eventData) {
        return calendarManager.addEvent(eventData);
    }

    @Override
    public boolean completeEvent(long eventId) {
        return calendarManager.markEventAsCompleted(eventId);
    }
}
