package entity.dates;

import java.util.LinkedList;
import java.util.List;

/**
 * Compound multiple strategies
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
