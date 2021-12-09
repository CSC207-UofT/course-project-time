package gui.utility;

import com.calendarfx.model.Entry;
import consoleapp.eventadapters.CalendarEventData;
import datagateway.event.EventReader;
import services.eventcreation.CalendarEventModel;
import services.eventpresentation.EventInfo;
import services.strategybuilding.MultipleRuleFormBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * A helper class that contains methods related
 * to converting between event data from the backend
 * to data required by the GUI
 */
public class EventHelper {

    /**
     * Given an EventInfo, extract relevant information and create and new entry for the view
     * @param eventInfo DTO that holds relevant information for creating an Entry
     * @return an entry for the view to present
     */
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

    /**
     * Given an EventReader, extract relevant information and create and new entry for the view
     * @param eventReader DTO that holds relevant information for creating an Entry
     * @return an entry for the view to present
     */
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
