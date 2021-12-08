package services.strategybuilding;

import entity.dates.DateStrategy;

/**
 * Directs a StrategyBuilder using Rules taken from a DatesForm (iterable of Rules).
 */
public class StrategyBuilderDirector {

    public DateStrategy createStrategy(DatesForm form) {

        StrategyBuilder sb = new StrategyBuilder();

        for (Rule r : form)
            r.execute(sb);

        return sb.compileStrategy();
    }

}
