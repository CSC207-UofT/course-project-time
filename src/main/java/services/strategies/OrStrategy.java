package services.strategies;

import entity.dates.CompositeDateStrategy;
import entity.dates.DateStrategy;
import entity.dates.TimeFrame;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Combines multiple strategies and accepts all generated dates
 *
 * Useful for combining logically unrelated strategies
 */
public class OrStrategy extends CompositeDateStrategy {

    @Override
    public List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration) {
        Set<TimeFrame> acceptedDates = new HashSet<>();

        for (DateStrategy strategy : super.getStrategies())
            acceptedDates.addAll(strategy.datesBetween(startDateTime, endDateTime, eventDuration));

        return new ArrayList<>(acceptedDates);
    }

    @Override
    public String toString() {
        List<String> innerStrings = new ArrayList<>();
        getStrategies().forEach(s -> innerStrings.add(s.toString()));
        return String.join(" and ", innerStrings);
    }

}
