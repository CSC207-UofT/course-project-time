package services.strategy_building;

/**
 * Logic that directly manipulates the StrategyBuilder to build a strategy.
 */
public interface Rule {
    void execute(StrategyBuilder sb);
}