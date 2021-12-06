package data_gateway.event;

import java.io.IOException;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public interface CalendarManager {

    long addEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, HashSet<String> tags,
                  LocalDate date);

    void markEventAsCompleted(long eventId);

    List<EventReader> getAllEvents();

    void updateName(long id, String newName);

    void updateStartTime(long id, LocalTime newStartTime);

    void updateEndTime(long id, LocalTime newEndTime);

    void addTag(long id, String tag);

    void removeTag(long id, String tag);

    void loadEvents(String filePath) throws IOException;

    void saveEvents(String savePath) throws IOException;

}
