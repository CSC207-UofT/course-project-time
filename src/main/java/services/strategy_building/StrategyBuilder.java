package services.strategy_building;

import entity.dates.CompositeDateStrategy;
import entity.dates.DateStrategy;
import entity.dates.OrStrategy;
import services.strategy_building.strategies.EndRestrictionDecorator;
import services.strategy_building.strategies.SingleDateStrategy;
import services.strategy_building.strategies.StartRestrictionDecorator;
import services.strategy_building.strategies.WeeklyStrategy;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Stack;

/**
 * DateStrategy builder class encapsulating the logic and invariants of trying to combine DateStrategy classes.
 */
public class StrategyBuilder {

    private DateStrategy currentStrategy = null;
    private final Stack<CompositeDateStrategy> compositeStrategiesStack = new Stack<>();

    public void startWeeklyStrategy(DayOfWeek dayOfWeek, LocalTime timeOfDay) {
        currentStrategy = new WeeklyStrategy(dayOfWeek, timeOfDay);
    }

    public void startSingleDateStrategy(LocalDateTime dateTime) {
        currentStrategy = new SingleDateStrategy(dateTime);
    }

    public void startUnionStrategy() {
        CompositeDateStrategy unionStrategy = new OrStrategy();
        currentStrategy = null;
        compositeStrategiesStack.clear();
        compositeStrategiesStack.add(unionStrategy);
    }

    public void addEndingRestriction(LocalDateTime endTime) {
        if (currentStrategy != null)
            currentStrategy = new EndRestrictionDecorator(currentStrategy, endTime);
        else if (!compositeStrategiesStack.isEmpty())
            currentStrategy = new EndRestrictionDecorator(compositeStrategiesStack.pop(), endTime);
    }

    public void addStartingRestriction(LocalDateTime startTime) {
        if (currentStrategy != null)
            currentStrategy = new StartRestrictionDecorator(currentStrategy, startTime);
        else if (!compositeStrategiesStack.isEmpty())
            currentStrategy = new StartRestrictionDecorator(compositeStrategiesStack.pop(), startTime);
    }

    public void addRangeRestriction(LocalDateTime startTime, LocalDateTime endTime) {
        addStartingRestriction(startTime);
        addEndingRestriction(endTime);
    }

    public void finishCurrentStrategy() {
        boolean unfinishedStrategyWhileCompositeOpen = currentStrategy != null && !compositeStrategiesStack.isEmpty();
        boolean noOpenStrategyButCompositeStacked = currentStrategy == null && !compositeStrategiesStack.isEmpty();
        if (unfinishedStrategyWhileCompositeOpen) {
            compositeStrategiesStack.peek().addStrategy(currentStrategy);
            currentStrategy = null;
        } else if (noOpenStrategyButCompositeStacked) {
            currentStrategy = compositeStrategiesStack.pop();
        }
    }

    public DateStrategy compileStrategy() {
        DateStrategy lastStrategy = currentStrategy;
        while (!compositeStrategiesStack.isEmpty()) {
            finishCurrentStrategy();
            lastStrategy = currentStrategy;
        }
        return lastStrategy;
    }

}
