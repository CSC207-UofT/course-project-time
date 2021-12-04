import entity.dates.DateStrategy;
import org.junit.jupiter.api.Test;
import services.strategy_building.StrategyBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrategyBuilderTest {

    final LocalDate arbitraryFriday = LocalDate.of(2021, 11, 19);

    @Test
    public void testWeeklyStrategyAcrossTwoWeeks() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startWeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT);
        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime twoWeeksLater = start.plusDays(14);

        List<LocalDateTime> actualDates = strategy.datesBetween(start, twoWeeksLater);


        LocalDate firstSunday = arbitraryFriday.plusDays(2);
        LocalDateTime firstTarget = LocalDateTime.of(firstSunday, LocalTime.MIDNIGHT);
        LocalDate secondSunday = firstSunday.plusDays(7);
        LocalDateTime secondTarget = LocalDateTime.of(secondSunday, LocalTime.MIDNIGHT);


        assertEquals(2, actualDates.size());
        assertTrue(actualDates.contains(firstTarget));
        assertTrue(actualDates.contains(secondTarget));
    }

    @Test
    public void testTwoDifferentWeeklyStrategies() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startUnionStrategy();

        sb.startWeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT);
        sb.finishCurrentStrategy();

        sb.startWeeklyStrategy(DayOfWeek.TUESDAY, LocalTime.NOON);
        sb.finishCurrentStrategy();
        sb.finishCurrentStrategy();

        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime weekAfter = start.plusDays(7);

        List<LocalDateTime> actualDates = strategy.datesBetween(start, weekAfter);


        LocalDate sunday = arbitraryFriday.plusDays(2);
        LocalDate tuesday = arbitraryFriday.plusDays(4);

        LocalDateTime sundayTarget = LocalDateTime.of(sunday, LocalTime.MIDNIGHT);
        LocalDateTime tuesdayTarget = LocalDateTime.of(tuesday, LocalTime.NOON);


        assertEquals(2, actualDates.size());
        assertTrue(actualDates.contains(sundayTarget));
        assertTrue(actualDates.contains(tuesdayTarget));
    }

    @Test
    public void testEndRestrictionOnWeekly() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startUnionStrategy();
        sb.startWeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT);

        LocalDate mondayAfterFirstSunday = arbitraryFriday.plusDays(3);
        sb.addEndingRestriction(mondayAfterFirstSunday.atStartOfDay());
        sb.finishCurrentStrategy();

        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime twoWeeksLater = start.plusDays(14);

        List<LocalDateTime> actualDates = strategy.datesBetween(start, twoWeeksLater);


        LocalDate firstSunday = arbitraryFriday.plusDays(2);
        LocalDate secondSunday = firstSunday.plusDays(7);

        LocalDateTime target = LocalDateTime.of(firstSunday, LocalTime.MIDNIGHT);
        LocalDateTime nonTarget = LocalDateTime.of(secondSunday, LocalTime.MIDNIGHT);


        assertEquals(1, actualDates.size());
        assertEquals(target, actualDates.get(0));
        assertFalse(actualDates.contains(nonTarget));
    }

    @Test
    public void testEndRestrictionOnComposite() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startUnionStrategy();
        sb.startWeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT);
        sb.finishCurrentStrategy();
        sb.startWeeklyStrategy(DayOfWeek.TUESDAY, LocalTime.NOON);
        sb.finishCurrentStrategy();

        LocalDate monday = arbitraryFriday.plusDays(3);
        sb.addEndingRestriction(monday.atStartOfDay());
        sb.finishCurrentStrategy();

        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime weekAfter = start.plusDays(7);

        List<LocalDateTime> actualDates = strategy.datesBetween(start, weekAfter);


        LocalDate sunday = arbitraryFriday.plusDays(2);
        LocalDateTime target = LocalDateTime.of(sunday, LocalTime.MIDNIGHT);


        assertEquals(1, actualDates.size());
        assertEquals(target, actualDates.get(0));
    }

    @Test
    public void testStartRestrictionOnWeekly() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startUnionStrategy();
        sb.startWeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT);

        LocalDate mondayAfterFirstSunday = arbitraryFriday.plusDays(3);
        sb.addStartingRestriction(mondayAfterFirstSunday.atStartOfDay());
        sb.finishCurrentStrategy();

        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime twoWeeksLater = start.plusDays(14);

        List<LocalDateTime> actualDates = strategy.datesBetween(start, twoWeeksLater);


        LocalDate firstSunday = arbitraryFriday.plusDays(2);
        LocalDate secondSunday = firstSunday.plusDays(7);

        LocalDateTime target = LocalDateTime.of(secondSunday, LocalTime.MIDNIGHT);
        LocalDateTime nonTarget = LocalDateTime.of(firstSunday, LocalTime.MIDNIGHT);


        assertEquals(1, actualDates.size());
        assertEquals(target, actualDates.get(0));
        assertFalse(actualDates.contains(nonTarget));
    }

    @Test
    public void testRangeRestrictionOnWeekly() {
        StrategyBuilder sb = new StrategyBuilder();
        sb.startUnionStrategy();
        sb.startWeeklyStrategy(DayOfWeek.SUNDAY, LocalTime.MIDNIGHT);

        LocalDate mondayAfterFirstSunday = arbitraryFriday.plusDays(3);
        LocalDate secondMondayAfterFirstSunday = mondayAfterFirstSunday.plusDays(7);
        sb.addRangeRestriction(mondayAfterFirstSunday.atStartOfDay(), secondMondayAfterFirstSunday.atStartOfDay());
        sb.finishCurrentStrategy();

        sb.finishCurrentStrategy();
        DateStrategy strategy = sb.compileStrategy();


        LocalDateTime start = arbitraryFriday.atStartOfDay();
        LocalDateTime threeWeeksLater = start.plusDays(21);

        List<LocalDateTime> actualDates = strategy.datesBetween(start, threeWeeksLater);


        LocalDate firstSunday = arbitraryFriday.plusDays(2);
        LocalDate secondSunday = firstSunday.plusDays(7);
        LocalDate thirdSunday = secondSunday.plusDays(7);

        LocalDateTime firstNonTarget = LocalDateTime.of(firstSunday, LocalTime.MIDNIGHT);
        LocalDateTime middleTarget = LocalDateTime.of(secondSunday, LocalTime.MIDNIGHT);
        LocalDateTime lastNonTarget = LocalDateTime.of(thirdSunday, LocalTime.MIDNIGHT);


        assertEquals(1, actualDates.size());
        assertEquals(middleTarget, actualDates.get(0));
        assertFalse(actualDates.contains(firstNonTarget));
        assertFalse(actualDates.contains(lastNonTarget));
    }
}
