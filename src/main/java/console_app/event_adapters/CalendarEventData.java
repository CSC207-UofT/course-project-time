package console_app.event_adapters;

import services.event_creation.CalendarEventModel;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public class CalendarEventData implements CalendarEventModel {

    private final String eventName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final HashSet<String> tags;
    private final Duration notificationTimeInAdvance;

    public CalendarEventData(String eventName, LocalDateTime startTime, LocalDateTime endTime,
                             HashSet<String> tags, Duration notificationTimeInAdvance) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tags = tags;
        this.notificationTimeInAdvance = notificationTimeInAdvance;
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

    @Override
    public Duration getNotificationTimeInAdvance() {
        return notificationTimeInAdvance;
    }
}
