package entity.dates;

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
    public List<LocalDateTime> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Set<LocalDateTime> acceptedDates = new HashSet<>();

        for (DateStrategy strategy : super.getStrategies())
            acceptedDates.addAll(strategy.datesBetween(startDateTime, endDateTime));

        return new ArrayList<>(acceptedDates);
    }

}