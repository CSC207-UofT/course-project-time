package services.strategy_building;

import entity.dates.DateStrategy;

public class StrategyBuilderDirector {

    public DateStrategy createStrategy(DatesForm form) {

        StrategyBuilder sb = new StrategyBuilder();

        sb.startUnionStrategy();
        for (Rule r : form)
            r.execute(sb);
        sb.finishCurrentStrategy();

        return sb.compileStrategy();
    }

}
