package services.strategybuilding.strategies;

import entity.dates.DateStrategy;
import entity.dates.DecoratorStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EndRestrictionDecorator extends DecoratorStrategy {

    private final LocalDateTime rangeEnd;

    public EndRestrictionDecorator(DateStrategy strategy, LocalDateTime rangeEnd) {
        super(strategy);
        this.rangeEnd = rangeEnd;
    }

    @Override
    public List<LocalDateTime> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<LocalDateTime> acceptedDates = new ArrayList<>();

        List<LocalDateTime> wrappedDates = super.datesBetween(startDateTime, endDateTime);
        for (LocalDateTime date : wrappedDates)
            if (date.isBefore(rangeEnd))
                acceptedDates.add(date);

        return acceptedDates;
    }
}
