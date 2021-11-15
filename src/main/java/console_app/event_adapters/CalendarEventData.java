package console_app.event_adapters;

import services.event_creation.CalendarEventModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public class CalendarEventData implements CalendarEventModel {

    private final String eventName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final HashSet<String> tags;

    public CalendarEventData(String eventName, LocalDateTime startTime, LocalDateTime endTime, HashSet<String> tags) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
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
    public HashSet<String> getTags() {
        return tags;
    }

    @Override
    public LocalDate getDate() {
        return startTime.toLocalDate();
    }
}
