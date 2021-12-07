package gui.utility;

import com.calendarfx.model.Entry;
import data_gateway.event.EventReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A helper class that contains methods related
 * to converting between event data from the backend
 * to data required by the GUI
 */
public class EventHelper {

    public static Entry<String> eventReaderToEntry(EventReader eventReader) {
        Entry<String> entry = new Entry<>(eventReader.getName());
        entry.changeStartTime(eventReader.getStartTime());
        entry.changeEndTime(eventReader.getEndTime());
        LocalDate date = LocalDate.of(2021, 12, 8);
        entry.changeStartDate(date);
        entry.changeEndDate(date);
        return entry;
    }

    public static List<Entry<String>> eventReaderToEntry(List<EventReader> eventReaders) {
        List<Entry<String>> entries = new ArrayList<>();
        for (EventReader reader : eventReaders) {
            entries.add(EventHelper.eventReaderToEntry(reader));
        }
        return entries;
    }
}
