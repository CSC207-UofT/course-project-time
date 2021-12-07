package gui.model;

import datagateway.event.CalendarManager;
import datagateway.event.EventReader;

import java.util.List;

public class EventModelManager {
    private final List<EventReader> eventReaderList;
    private final CalendarManager calendarManager;

    public EventModelManager(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
        this.eventReaderList = calendarManager.getAllEvents();
    }

    public List<EventReader> getEvents() {
        return this.eventReaderList;
    }
}
