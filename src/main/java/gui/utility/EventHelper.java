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

    public static List<Entry<String>> eventInfoToEntry(List<EventInfo> eventInfos) {
        List<Entry<String>> entries = new ArrayList<>();
        for (EventInfo eventInfo : eventInfos) {
            entries.add(EventHelper.eventInfoToEntry(eventInfo));
        }
        return entries;
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

    private static void updateEntryWithEventReader(Entry<String> entry, EventReader eventReader) {
        if (!entry.getTitle().equals(eventReader.getName())) {
            entry.setTitle(eventReader.getName());
        }
        if (!entry.getStartDate().equals(eventReader.getDates().iterator().next())) {
            entry.changeStartDate(eventReader.getDates().iterator().next());
        }
        if (!entry.getEndDate().equals(eventReader.getDates().iterator().next())) {
            entry.changeEndDate(eventReader.getDates().iterator().next());
        }
        if (!entry.getStartAsLocalDateTime().toLocalTime().equals(eventReader.getStartTime())) {
            entry.changeStartTime(eventReader.getStartTime());
        }
        if (!entry.getEndAsLocalDateTime().toLocalTime().equals(eventReader.getEndTime())) {
            entry.changeStartTime(eventReader.getEndTime());
        }
    }

    private static Entry<String> getEntryFromEvent(Map<String, Long> entryToEventIdMapping,
                                                   EventReader eventReader,
                                                   List<Entry<String>> entryList) {
        for (String entryId : entryToEventIdMapping.keySet()) {
            if (eventReader.getId() == entryToEventIdMapping.get(entryId)) {
                for (Entry<String> entry : entryList) {
                    if (entryId.equals(entry.getId())) {
                        return entry;
                    }
                }
            }
        }
        return null;
    }

    public static void updateEntry(Map<String, Long> entryToEventIdMapping,
                                   List<Entry<String>> entryList,
                                   EventReader eventReader) {
        if (entryToEventIdMapping.containsValue(eventReader.getId())) {
            Entry<String> entry = EventHelper.getEntryFromEvent(entryToEventIdMapping, eventReader, entryList);
            assert entry != null;
            EventHelper.updateEntryWithEventReader(entry, eventReader);
        } else {
            System.err.println("Event data may not be synchronized");
        }
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
