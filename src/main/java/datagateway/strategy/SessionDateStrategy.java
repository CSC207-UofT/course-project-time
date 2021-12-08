package datagateway.strategy;

import entity.dates.DateStrategy;
import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class SessionDateStrategy implements DateStrategy {

    private final long sessionId;
    private final DateStrategy strategy;

    public SessionDateStrategy(long sessionId, DateStrategy strategy) {
        this.sessionId = sessionId;
        this.strategy = strategy;
    }

    public long getSessionId() {
        return sessionId;
    }

    public List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration) {
        return strategy.datesBetween(startDateTime, endDateTime, eventDuration);
    }
}
