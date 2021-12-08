import entity.dates.DateStrategy;
import entity.dates.DecoratorStrategy;
import entity.dates.OrStrategy;
import entity.dates.TimeFrame;
import org.junit.jupiter.api.Test;
import services.strategybuilding.StrategyBuilder;
import services.strategybuilding.strategies.EndRestrictionDecorator;
import services.strategybuilding.strategies.StartRestrictionDecorator;
import services.strategybuilding.strategies.WeeklyStrategy;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrategyBuilderTest {

    final LocalDate arbitraryFriday = LocalDate.of(2021, 11, 19);

    private List<TimeFrame> sortTimeFrames(List<TimeFrame> timeFrames) {
        List<TimeFrame> sortedFrames = new ArrayList<>(timeFrames);
        sortedFrames.sort((a, b) -> a.startTime.isBefore(b.startTime) ? -1 : a.startTime.equals(b.startTime) ? 0 : 1);
        return sortedFrames;
    }

    @Test
    public void testWeeklyStrategyAcrossTwoWeeks() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT));
        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime twoWeeksLater = start.plusDays(14);

        List<TimeFrame> actualDates = strategy.datesBetween(start, twoWeeksLater, Duration.ofMinutes(5));
        List<TimeFrame> sortedFrames = sortTimeFrames(actualDates);

        LocalDate firstSunday = arbitraryFriday.plusDays(2);
        LocalDateTime firstTarget = LocalDateTime.of(firstSunday, LocalTime.MIDNIGHT);
        LocalDate secondSunday = firstSunday.plusDays(7);
        LocalDateTime secondTarget = LocalDateTime.of(secondSunday, LocalTime.MIDNIGHT);

        assertEquals(2, sortedFrames.size());
        assertEquals(sortedFrames.get(0).startTime, firstTarget);
        assertEquals(sortedFrames.get(1).startTime, secondTarget);
    }

    @Test
    public void testTwoDifferentWeeklyStrategies() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startCompositeStrategy(new OrStrategy());

        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT));
        sb.finishCurrentStrategy();

        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.TUESDAY, LocalTime.NOON));
        sb.finishCurrentStrategy();
        sb.finishCurrentStrategy();

        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime weekAfter = start.plusDays(7);

        List<TimeFrame> actualDates = strategy.datesBetween(start, weekAfter, Duration.ofMinutes(5));
        List<TimeFrame> sortedFrames = sortTimeFrames(actualDates);


        LocalDate sunday = arbitraryFriday.plusDays(2);
        LocalDate tuesday = arbitraryFriday.plusDays(4);

        LocalDateTime sundayTarget = LocalDateTime.of(sunday, LocalTime.MIDNIGHT);
        LocalDateTime tuesdayTarget = LocalDateTime.of(tuesday, LocalTime.NOON);


        assertEquals(2, sortedFrames.size());
        assertEquals(sortedFrames.get(0).startTime, sundayTarget);
        assertEquals(sortedFrames.get(1).startTime, tuesdayTarget);
    }

    @Test
    public void testEndRestrictionOnWeekly() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startCompositeStrategy(new OrStrategy());
        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT));

        LocalDate mondayAfterFirstSunday = arbitraryFriday.plusDays(3);
        DecoratorStrategy endStrategy = new EndRestrictionDecorator(mondayAfterFirstSunday.atStartOfDay());
        sb.addDecorator(endStrategy);
        sb.finishCurrentStrategy();

        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime twoWeeksLater = start.plusDays(14);

        List<TimeFrame> actualDates = strategy.datesBetween(start, twoWeeksLater, Duration.ofMinutes(5));
        List<TimeFrame> sortedFrames = sortTimeFrames(actualDates);


        LocalDate firstSunday = arbitraryFriday.plusDays(2);

        LocalDateTime target = LocalDateTime.of(firstSunday, LocalTime.MIDNIGHT);


        assertEquals(1, sortedFrames.size());
        assertEquals(target, sortedFrames.get(0).startTime);
    }

    @Test
    public void testEndRestrictionOnComposite() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startCompositeStrategy(new OrStrategy());
        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT));
        sb.finishCurrentStrategy();
        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.TUESDAY, LocalTime.NOON));
        sb.finishCurrentStrategy();

        LocalDate monday = arbitraryFriday.plusDays(3);
        DecoratorStrategy endStrategy = new EndRestrictionDecorator(monday.atStartOfDay());
        sb.addDecorator(endStrategy);
        sb.finishCurrentStrategy();

        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime weekAfter = start.plusDays(7);

        List<TimeFrame> actualDates = strategy.datesBetween(start, weekAfter, Duration.ofMinutes(5));
        List<TimeFrame> sortedFrames = sortTimeFrames(actualDates);


        LocalDate sunday = arbitraryFriday.plusDays(2);
        LocalDateTime target = LocalDateTime.of(sunday, LocalTime.MIDNIGHT);


        assertEquals(1, sortedFrames.size());
        assertEquals(target, sortedFrames.get(0).startTime);
    }

    @Test
    public void testStartRestrictionOnWeekly() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startCompositeStrategy(new OrStrategy());
        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT));

        LocalDate mondayAfterFirstSunday = arbitraryFriday.plusDays(3);
        DecoratorStrategy startStrategy = new StartRestrictionDecorator(mondayAfterFirstSunday.atStartOfDay());
        sb.addDecorator(startStrategy);
        sb.finishCurrentStrategy();

        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime twoWeeksLater = start.plusDays(14);

        List<TimeFrame> actualDates = strategy.datesBetween(start, twoWeeksLater, Duration.ofMinutes(5));
        List<TimeFrame> sortedFrames = sortTimeFrames(actualDates);


        LocalDate firstSunday = arbitraryFriday.plusDays(2);
        LocalDate secondSunday = firstSunday.plusDays(7);

        LocalDateTime target = LocalDateTime.of(secondSunday, LocalTime.MIDNIGHT);


        assertEquals(1, sortedFrames.size());
        assertEquals(target, sortedFrames.get(0).startTime);
    }

    @Test
    public void testRangeRestrictionOnWeekly() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startCompositeStrategy(new OrStrategy());
        sb.startBaseStrategy(new WeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT));

        LocalDate mondayAfterFirstSunday = arbitraryFriday.plusDays(3);
        LocalDate secondMondayAfterFirstSunday = mondayAfterFirstSunday.plusDays(7);
        DecoratorStrategy startStrategy = new StartRestrictionDecorator(mondayAfterFirstSunday.atStartOfDay());
        DecoratorStrategy endStrategy = new EndRestrictionDecorator(secondMondayAfterFirstSunday.atStartOfDay());
        sb.addDecorator(startStrategy);
        sb.addDecorator(endStrategy);
        sb.finishCurrentStrategy();

        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime threeWeeksLater = start.plusDays(21);

        List<TimeFrame> actualDates = strategy.datesBetween(start, threeWeeksLater, Duration.ofMinutes(5));
        List<TimeFrame> sortedFrames = sortTimeFrames(actualDates);


        LocalDate firstSunday = arbitraryFriday.plusDays(2);
        LocalDate secondSunday = firstSunday.plusDays(7);

        LocalDateTime middleTarget = LocalDateTime.of(secondSunday, LocalTime.MIDNIGHT);


        assertEquals(1, sortedFrames.size());
        assertEquals(middleTarget, sortedFrames.get(0).startTime);
    }
}
