package services.strategybuilding.strategies;

import entity.dates.DecoratorStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StartRestrictionDecorator extends DecoratorStrategy {

    private final LocalDateTime rangeStart;

    public StartRestrictionDecorator(LocalDateTime rangeStart) {
        this.rangeStart = rangeStart;
    }

    @Override
    public List<LocalDateTime> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<LocalDateTime> acceptedDates = new ArrayList<>();

        List<LocalDateTime> wrappedDates = super.datesBetween(startDateTime, endDateTime);
        for (LocalDateTime date : wrappedDates)
            if (date.isAfter(rangeStart))
                acceptedDates.add(date);

        return acceptedDates;
    }
}
