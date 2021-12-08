package services.strategybuilding.strategies;

import entity.dates.DecoratorStrategy;
import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StartRestrictionDecorator extends DecoratorStrategy {

    private final LocalDateTime rangeStart;

    public StartRestrictionDecorator(LocalDateTime rangeStart) {
        this.rangeStart = rangeStart;
    }

    @Override
    public List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration) {
        List<TimeFrame> acceptedDates = new ArrayList<>();

        List<TimeFrame> wrappedDates = super.datesBetween(startDateTime, endDateTime, eventDuration);
        for (TimeFrame date : wrappedDates)
            if (date.startTime.isAfter(rangeStart))
                acceptedDates.add(date);

        return acceptedDates;
    }
}
