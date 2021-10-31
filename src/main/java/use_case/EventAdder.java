package main.java.use_case;

import main.java.entity_gateway.CalendarManager;


public class EventAdder implements CalendarEventCreationBoundary {

    private final CalendarManager calendarManager;

    public EventAdder(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    public boolean addEvent(CalendarEventModel eventData) {
        return calendarManager.addEvent(eventData);
    }

}
