package services.strategybuilding.strategies;

import entity.dates.DateStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SingleDateStrategy implements DateStrategy {

    private final LocalDateTime eventTime;

    public SingleDateStrategy(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }


    @Override
    public List<LocalDateTime> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<LocalDateTime> dates = new ArrayList<>();
        if (eventTime.isAfter(startDateTime) && eventTime.isBefore(endDateTime))
            dates.add(eventTime);
        return dates;
    }
}
