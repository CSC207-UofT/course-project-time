package services.strategies;

import entity.dates.DecoratorStrategy;
import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EndRestrictionDecorator extends DecoratorStrategy {

    private final LocalDateTime rangeEnd;

    public EndRestrictionDecorator(LocalDateTime rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    @Override
    public List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration) {
        List<TimeFrame> acceptedDates = new ArrayList<>();

        List<TimeFrame> wrappedDates = super.datesBetween(startDateTime, endDateTime, eventDuration);
        for (TimeFrame date : wrappedDates)
            if (date.startTime.plus(date.duration).isBefore(rangeEnd))
                acceptedDates.add(date);

        return acceptedDates;
    }

    @Override
    public String toString() {
        return super.toString() + " until " + rangeEnd.toString();
    }
}
