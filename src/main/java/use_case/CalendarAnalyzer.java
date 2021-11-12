package main.java.use_case;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface CalendarAnalyzer {

    boolean isAvailable(LocalTime startTime, Duration timeNeeded, LocalDate date);

    LocalDateTime getAvailableTime(List<LocalDateTime> timesToIgnore, Duration taskDuration);

}