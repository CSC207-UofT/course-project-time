package services.strategy_building;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultipleBasicForm implements DatesForm {

    private final List<Rule> rules = new ArrayList<>();

    @Override
    public Iterator<Rule> iterator() {
        List<Rule> withinUnionStrategy = new ArrayList<>();

        withinUnionStrategy.add(new Rules.StartUnionRule());
        withinUnionStrategy.addAll(rules);
        withinUnionStrategy.add(new Rules.FinishRule());

        return withinUnionStrategy.listIterator();
    }

    public void addSingleOccurrence(LocalDateTime dateTime) {
        rules.add(new Rules.StartSingleDateRule(dateTime));
        addFinishRule();
    }

    public void addWeeklyOccurrence(DayOfWeek day, LocalTime timeOfDay) {
        rules.add(new Rules.StartWeeklyRule(day, timeOfDay));
        addFinishRule();
    }

    public void addWeeklyOccurrenceFrom(DayOfWeek day, LocalTime timeOfDay, LocalDateTime startFrom) {
        rules.add(new Rules.StartWeeklyRule(day, timeOfDay));
        rules.add(new Rules.OneSidedRestrictionRule(startFrom, Rules.OneSidedRestrictionRule.IS_START));
        addFinishRule();
    }
    public void addWeeklyOccurrenceUntil(DayOfWeek day, LocalTime timeOfDay, LocalDateTime endAt) {
        rules.add(new Rules.StartWeeklyRule(day, timeOfDay));
        rules.add(new Rules.OneSidedRestrictionRule(endAt, !Rules.OneSidedRestrictionRule.IS_START));
        addFinishRule();
    }

    public void addWeeklyOccurrenceBetween(DayOfWeek day, LocalTime timeOfDay,
                                           LocalDateTime startFrom, LocalDateTime endAt) {
        rules.add(new Rules.StartWeeklyRule(day, timeOfDay));
        rules.add(new Rules.BoundedRestrictionRule(startFrom, endAt));
        addFinishRule();
    }

    private void addFinishRule() {
        rules.add(new Rules.FinishRule());
    }
}