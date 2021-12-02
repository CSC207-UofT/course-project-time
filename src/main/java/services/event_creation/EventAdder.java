package services.event_creation;

import data_gateway.CalendarManager;

public class EventAdder implements CalendarEventCreationBoundary {

    private final CalendarManager calendarManager;

    public EventAdder(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {
        return calendarManager.addEvent(eventData);
    }

}
