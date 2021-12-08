package services.strategybuilding;

import entity.dates.CompositeDateStrategy;
import entity.dates.DateStrategy;
import entity.dates.DecoratorStrategy;

import java.util.Stack;

/**
 * DateStrategy builder class encapsulating the logic and invariants of trying to combine DateStrategy classes.
 */
public class StrategyBuilder {

    private DateStrategy currentStrategy = null;
    private final Stack<CompositeDateStrategy> compositeStrategiesStack = new Stack<>();

    public void startBaseStrategy(DateStrategy strategy) {
        currentStrategy = strategy;
    }

    public void startCompositeStrategy(CompositeDateStrategy compositeStrategy) {
        currentStrategy = null;
        compositeStrategiesStack.clear();
        compositeStrategiesStack.add(compositeStrategy);
    }

    public void addDecorator(DecoratorStrategy decorator) {
        if (currentStrategy != null) {
            decorator.setStrategy(currentStrategy);
            currentStrategy = decorator;
        }
        else if (!compositeStrategiesStack.isEmpty()) {
            decorator.setStrategy(compositeStrategiesStack.pop());
            currentStrategy = decorator;
        }
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
