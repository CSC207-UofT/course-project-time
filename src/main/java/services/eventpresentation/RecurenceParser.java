package services.eventpresentation;

import entity.dates.DateStrategy;
import services.strategybuilding.MultipleRuleFormBuilder;
import services.strategybuilding.StrategyBuilderDirector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// Based on RFC2445 docs: https://datatracker.ietf.org/doc/html/rfc2445#section-4.8.5
public class RecurenceParser {

    DateStrategy getStrategy(LocalDateTime eventTime, String recurrence) {
        MultipleRuleFormBuilder form = new MultipleRuleFormBuilder();
        StrategyBuilderDirector builder = new StrategyBuilderDirector();
        if(recurrence.contains("WEEKLY")) {

            if(getEndTime(recurrence) == null) {
                form.addWeeklyOccurrence(eventTime.getDayOfWeek(), eventTime.toLocalTime());
            } else {
                form.addWeeklyOccurrenceUntil(eventTime.getDayOfWeek(), eventTime.toLocalTime(), getEndTime(recurrence));
            }
        }

        if(recurrence.contains("DAILY")) {
        }

        if(recurrence.contains("MONTHLY")) {

        }

        if(recurrence.contains("YEARLY")) {

        }

        return builder.createStrategy(form.getForm());
    }
// 19980415T170000Z
    LocalDateTime getEndTime(String recurence) {
        int index = recurence.indexOf("UNTIL=");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        if(index != -1 && recurence.length() >= index + 14) {

            return LocalDateTime.parse(recurence.substring(index + 6, index + 13), dateFormat);

        } else {
            return null;
        }
    }

}
