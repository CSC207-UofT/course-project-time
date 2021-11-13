package main.java.entity_gateway;

import main.java.use_case.CalendarEventModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CalendarManager {

    boolean addEvent(CalendarEventModel eventData);

    List<EventReader> getAllEvents();

    void loadEvents(String filePath) throws IOException;

    void saveEvents(String savePath) throws IOException;

}
