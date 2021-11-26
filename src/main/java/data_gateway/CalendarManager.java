package data_gateway;

import entity.Event;
import services.event_creation.CalendarEventModel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface CalendarManager {

    long addEvent(CalendarEventModel eventData);

    void markEventAsCompleted(long eventId);

    List<EventReader> getAllEvents();

    void updateName(long id, String newName);

    void updateStartTime(long id, LocalTime newStartTime);

    void updateEndTime(long id, LocalTime newEndTime);

    void loadEvents(String filePath) throws IOException;

    void saveEvents(String savePath) throws IOException;

}
