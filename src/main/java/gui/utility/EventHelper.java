package gui.utility;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;
import consoleapp.eventadapters.CalendarEventData;
import datagateway.event.EventReader;
import services.eventcreation.CalendarEventModel;
import services.eventpresentation.EventInfo;
import services.strategybuilding.MultipleRuleFormBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * A helper class that contains methods related
 * to converting between event data from the backend
 * to data required by the GUI
 */
public class EventHelper {

    public static Entry<String> eventInfoToEntry(EventInfo eventInfo) {
        Entry<String> entry = new Entry<>(eventInfo.getName());
        entry.changeStartTime(eventInfo.getStartTime());
        entry.changeEndTime(eventInfo.getEndTime());
        LocalDate date = eventInfo.getDates().iterator().next();
        entry.changeStartDate(date);
        entry.changeEndDate(date);
        return entry;
    }

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

    public static Entry<String> eventReaderToEntry(EventReader eventReader) {
        Entry<String> entry = new Entry<>(eventReader.getName());
        entry.changeStartTime(eventReader.getStartTime());
        entry.changeEndTime(eventReader.getEndTime());
        LocalDate date = eventReader.getDates().iterator().next();
        entry.changeStartDate(date);
        entry.changeEndDate(date);
        return entry;
    }
}
