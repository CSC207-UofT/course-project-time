package gui.utility;

import com.calendarfx.model.Entry;
import datagateway.event.EventReader;
import services.eventpresentation.EventInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        LocalDate date = LocalDate.of(2021, 12, 8);
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
}
