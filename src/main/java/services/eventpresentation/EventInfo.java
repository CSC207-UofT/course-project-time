package services.eventpresentation;

import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public interface EventInfo {
    long getId();

    String getName();

    Duration getDuration();

    Set<String> getTags();

    Set<TimeFrame> getDatesBetween(LocalDateTime startTime, LocalDateTime endTime);

    String getWhen();

    boolean getCompleted();
}
