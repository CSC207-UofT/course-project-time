package services.strategybuilding.strategies;

import entity.dates.DateStrategy;
import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SingleDateStrategy implements DateStrategy {

    private final LocalDateTime eventTime;

    public SingleDateStrategy(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }


    @Override
    public List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration) {
        List<TimeFrame> dates = new ArrayList<>();
        if (eventTime.isAfter(startDateTime) && eventTime.isBefore(endDateTime))
            dates.add(new TimeFrame(eventTime, eventTime.plus(eventDuration)));
        return dates;
    }
}
