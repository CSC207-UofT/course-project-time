package datagateway.event;

import entity.dates.DateStrategy;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CalendarManager {

    long addEvent(String eventName, DateStrategy strategy, Duration duration, Set<String> tags);

    long addEvent(long taskId, DateStrategy dateStrategy, Set<String> tags);

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
