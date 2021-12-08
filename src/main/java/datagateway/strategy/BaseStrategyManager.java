package datagateway.strategy;

import entity.dates.DateStrategy;
import services.Snowflake;

import java.util.ArrayList;
import java.util.List;

public class BaseStrategyManager implements DateStrategyManager {

    private final Snowflake snowflake;
    private final List<SessionDateStrategy> strategies = new ArrayList<>();

    public BaseStrategyManager(Snowflake snowflake) {
        this.snowflake = snowflake;
    }


    @Override
    public long addStrategy(DateStrategy strategy) {
        long sessionId = snowflake.nextId();
        strategies.add(new SessionDateStrategy(sessionId, strategy));
        return sessionId;
    }


}
