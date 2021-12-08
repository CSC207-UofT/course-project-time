package datagateway.strategy;

import entity.dates.DateStrategy;

public interface DateStrategyManager {

    /**
     * Provides a session id to the strategy
     * @param strategy an arbitrary strategy to store with an associated id
     * @return the generated id associated with the strategy
     */
    long addStrategy(DateStrategy strategy);

}
