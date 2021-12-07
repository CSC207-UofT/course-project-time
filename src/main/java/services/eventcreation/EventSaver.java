package services.eventcreation;

import datagateway.event.CalendarManager;

import java.io.IOException;

public class EventSaver {

    private final CalendarManager calendarManager;
    public EventSaver(CalendarManager eventData)
    {
        calendarManager = eventData;
    }
    public void saveEventData(String filename) throws IOException {
        calendarManager.saveEvents(filename);
    }
}
