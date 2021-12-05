package services.strategy_building.strategies;

import entity.dates.DateStrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeeklyStrategy implements DateStrategy {

    private final DayOfWeek dayOfWeek;
    private final LocalTime timeOfDay;

    public WeeklyStrategy(DayOfWeek dayOfWeek, LocalTime timeOfDay) {
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
    }

    @Override
    public List<LocalDateTime> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<LocalDateTime> acceptedDates = new ArrayList<>();

        LocalDateTime date = findFirstDate(startDateTime);
        while (date.isBefore(endDateTime)) {
            acceptedDates.add(date);

            date = date.plusDays(7);
        }

        return acceptedDates;
    }

    private LocalDateTime findFirstDate(LocalDateTime startDateTime) {
        int daysToTarget = (dayOfWeek.getValue() + 7 - startDateTime.getDayOfWeek().getValue()) % 7;

        LocalDate firstDate = startDateTime.toLocalDate().plusDays(daysToTarget);
        LocalDateTime firstDateTime = LocalDateTime.of(firstDate, timeOfDay);

        return firstDateTime.isAfter(startDateTime) ? firstDateTime : firstDateTime.plusDays(7);
    }
}
