package main.java.use_case;

import main.java.entity.Event;
import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.EventEntityManager;

import java.io.IOException;

public class EventSaver {

    private CalendarManager calendarManager;
    public EventSaver(CalendarManager eventData)
    {
        calendarManager = eventData;
    }
    public void SaveEventData(String filename) throws IOException {
        calendarManager.saveEvents(filename);
    }
}
