package entity.dates;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Modify the dates returned by another strategy
 */
abstract public class DecoratorStrategy implements DateStrategy {

    private final DateStrategy strategy;

    public DecoratorStrategy(DateStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<LocalDateTime> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return strategy.datesBetween(startDateTime, endDateTime);
    }
}
