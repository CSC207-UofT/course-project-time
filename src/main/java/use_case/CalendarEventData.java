package main.java.use_case;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class CalendarEventData implements CalendarEventCreationDTO {

    private final String eventName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final Set<String> tags;
    private final LocalDate dates;

    public CalendarEventData(String eventName, LocalDateTime startTime, LocalDateTime endTime, Set<String> tags, LocalDate dates) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        this.dates = dates;
    }

    @Override
    public String getName() {
        return eventName;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }

    @Override
    public LocalDate getDate() {
        return dates;
    }
}
