package services.event_creation;

import data_gateway.CalendarManager;

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
