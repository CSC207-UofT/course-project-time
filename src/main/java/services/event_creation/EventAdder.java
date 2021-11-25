package services.event_creation;

import data_gateway.CalendarManager;

public class EventAdder implements EventAdding {

    private final CalendarManager calendarManager;

    public EventAdder(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public void addEvent(CalendarEventModel eventData) {
        calendarManager.addEvent(eventData);
    }

    @Override
    public void markEventAsCompleted(long eventId) {
        calendarManager.markEventAsCompleted(eventId);
    }
}
