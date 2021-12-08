package entity.dates;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Modify the dates returned by another strategy
 */
abstract public class DecoratorStrategy implements DateStrategy {

    private DateStrategy strategy;

    public void setStrategy(DateStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration) {
        return strategy.datesBetween(startDateTime, endDateTime, eventDuration);
    }
}
