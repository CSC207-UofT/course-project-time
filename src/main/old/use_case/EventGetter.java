package main.old.use_case;

import main.old.entity.Calendar;
import main.old.entity.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventGetter implements GetEvent {
    Calendar calendar;

    public EventGetter(AccessCalendarData data) {
        this.calendar = data.getCalendar();
    }

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values, with keys as "name", "start",
     * "end", "tags", and "dates
     */
    @Override
    public List<HashMap<String, String>> getEvents() {
        List<HashMap<String, String>> event_data = new ArrayList<>();
        for(Event event : calendar.getEvents()) {
            event_data.add(getEvent(event));
        }

        return event_data;
    }

    private HashMap<String, String> getEvent(Event event) {
        HashMap<String, String> event_data = new HashMap<>();
        event_data.put("name", event.getEventName());
        event_data.put("start", event.getStartTime().toString());
        event_data.put("end", event.getEndTime().toString());
        event_data.put("tags", event.getTags().toString());
        event_data.put("dates", event.getDates().toString());

        return event_data;

    }
}
