package entity.dates;

import java.util.LinkedList;
import java.util.List;

/**
 * Compound dates generated by multiple other strategies
 */
abstract public class CompositeDateStrategy implements DateStrategy {

    private final List<DateStrategy> strategies = new LinkedList<>();

    public void addStrategy(DateStrategy strategy) {
        strategies.add(strategy);
    }

    public List<DateStrategy> getStrategies() {
        return new LinkedList<>(strategies);
    }

}