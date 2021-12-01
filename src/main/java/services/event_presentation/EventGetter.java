package services.event_presentation;

import data_gateway.CalendarManager;
import data_gateway.EventReader;
import services.event_creation.EventInfoFromReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventGetter implements CalendarEventRequestBoundary {

    private final CalendarManager calendarManager;

    public EventGetter(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    /**
     * Get events from the database through data gateway and
     * pass the events as DTOs to the presenter to present all events.
     */
    @Override
    public List<HashMap<String, String>> getEvents() {
        List<HashMap<String, String>> event_data = new ArrayList<>();
        for(EventReader event : calendarManager.getAllEvents()) {
            event_data.add(getEvent(event));
        }

        return event_data;
    }

    private HashMap<String, String> getEvent(EventReader event) {
        HashMap<String, String> event_data = new HashMap<>();
        event_data.put("name", event.getName());
        event_data.put("start", event.getStartTime().toString());
        event_data.put("end", event.getEndTime().toString());
        event_data.put("tags", event.getTags().toString());
        event_data.put("dates", event.getDates().toString());

        return event_data;

    }

    @Override
    public EventInfo getEventByName(String name) {
        List<EventReader> allEvents = calendarManager.getAllEvents();
        for (EventReader event: allEvents) {
            if (event.getName().equals(name)) {
                return new EventInfoFromReader(event);
            }
        }
        return null;
    }
}
