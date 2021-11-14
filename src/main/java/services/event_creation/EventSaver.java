package main.java.services.event_creation;

import main.java.entity.Event;
import main.java.data_gateway.CalendarManager;

import java.io.IOException;

public class EventSaver {

    private CalendarManager calendarManager;
    public EventSaver(CalendarManager eventData)
    {
        calendarManager = eventData;
    }
    public void saveEventData(String filename) throws IOException {
        calendarManager.saveEvents(filename);
    }
}
