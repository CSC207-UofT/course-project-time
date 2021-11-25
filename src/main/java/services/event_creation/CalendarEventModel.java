package services.event_creation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public interface CalendarEventModel {
    String getName();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    HashSet<String> getTags();
    LocalDate getDate();
    Duration getNotificationTimeInAdvance();

}
