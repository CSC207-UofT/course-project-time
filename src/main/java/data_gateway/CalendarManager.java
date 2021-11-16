package data_gateway;

import services.event_creation.CalendarEventModel;

import java.io.IOException;
import java.util.List;

public interface CalendarManager {

    void addEvent(CalendarEventModel eventData);

    void markEventAsCompleted(long eventId);

    List<EventReader> getAllEvents();

    void loadEvents(String filePath) throws IOException;

    void saveEvents(String savePath) throws IOException;

}
