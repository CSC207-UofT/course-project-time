package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventGetter implements GetEvent {
    Calendar calendar;

    public EventGetter(AccessCalendarData data)
    {
        this.calendar = data.getCalendar();
    }

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
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
        String event_name = event.getEventName();
        String event_start = event.getStartTime().toString();
        String event_end = event.getEndTime().toString();
        HashMap<String, String> event_data = new HashMap<>();
        event_data.put("name", event_name);
        event_data.put("start", event_start);
        event_data.put("end", event_end);

        return event_data;

    }
}
