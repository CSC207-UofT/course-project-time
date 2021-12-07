package services.strategybuilding;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Builder with declarative methods that fills up the form with rules.
 */
public class MultipleRuleFormBuilder {

    private final DatesForm form = new UnionForm();

    public DatesForm getForm() {
        return form;
    }

    public void addSingleOccurrence(LocalDateTime dateTime) {
        form.addRule(new Rules.StartSingleDateRule(dateTime));
        addFinishRule();
    }

    public void addWeeklyOccurrence(DayOfWeek day, LocalTime timeOfDay) {
        form.addRule(new Rules.StartWeeklyRule(day, timeOfDay));
        addFinishRule();
    }

    public void addWeeklyOccurrenceFrom(DayOfWeek day, LocalTime timeOfDay, LocalDateTime startFrom) {
        form.addRule(new Rules.StartWeeklyRule(day, timeOfDay));
        form.addRule(new Rules.OneSidedRestrictionRule(startFrom, Rules.OneSidedRestrictionRule.IS_START));
        addFinishRule();
    }
    public void addWeeklyOccurrenceUntil(DayOfWeek day, LocalTime timeOfDay, LocalDateTime endAt) {
        form.addRule(new Rules.StartWeeklyRule(day, timeOfDay));
        form.addRule(new Rules.OneSidedRestrictionRule(endAt, !Rules.OneSidedRestrictionRule.IS_START));
        addFinishRule();
    }

    public void addWeeklyOccurrenceBetween(DayOfWeek day, LocalTime timeOfDay,
                                           LocalDateTime startFrom, LocalDateTime endAt) {
        form.addRule(new Rules.StartWeeklyRule(day, timeOfDay));
        form.addRule(new Rules.BoundedRestrictionRule(startFrom, endAt));
        addFinishRule();
    }

    private void addFinishRule() {
        form.addRule(new Rules.FinishRule());
    }
}