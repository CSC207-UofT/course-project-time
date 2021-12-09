package services.strategies;

import entity.dates.DateStrategy;
import entity.dates.TimeFrame;

import java.time.DayOfWeek;
import java.time.Duration;
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
    public List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration) {
        List<TimeFrame> acceptedDates = new ArrayList<>();

        // offset the date by the duration to include time frames starting before startDateTime but ending after.
        LocalDateTime date = findFirstDate(startDateTime.minus(eventDuration));
        while (date.isBefore(endDateTime)) {
            acceptedDates.add(new TimeFrame(date, date.plus(eventDuration)));

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

    @Override
    public String toString() {
        return "occur every week on " + dayOfWeek.toString() + " at " + timeOfDay.toString();
    }
}
