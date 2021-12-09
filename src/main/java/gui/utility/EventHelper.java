package gui.utility;

import com.calendarfx.model.Entry;
import consoleapp.eventadapters.CalendarEventData;
import datagateway.event.EventReader;
import entity.dates.TimeFrame;
import services.eventcreation.CalendarEventModel;
import services.eventpresentation.EventInfo;
import services.strategybuilding.MultipleRuleFormBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * A helper class that contains methods related
 * to converting between event data from the backend
 * to data required by the GUI
 */
public class EventHelper {

    /**
     * Returns an event model from the name and time of the entry, with a default form.
     * @param entry the entry created from a view
     * @return an event model (DTO)
     */
    public static CalendarEventModel entryToCalendarEventModel(Entry<String> entry) {
        Duration duration = Duration.between(entry.getStartAsLocalDateTime(), entry.getEndAsLocalDateTime());
        MultipleRuleFormBuilder builder = new MultipleRuleFormBuilder();
        builder.addSingleOccurrence(entry.getStartAsLocalDateTime());
        return new CalendarEventData(entry.getTitle(),
                duration, builder.getForm(), new HashSet<>());
    }

    public static Set<TimeFrame> getTimesFromStaticRange(BiFunction<LocalDateTime, LocalDateTime, Set<TimeFrame>> dateStrategy) {
        LocalDateTime from = LocalDateTime.now().minusYears(2);
        LocalDateTime to = LocalDateTime.now().plusYears(2);
        return dateStrategy.apply(from, to);
    }

}
