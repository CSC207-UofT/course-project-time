package main.java.services.event_presentation;

import main.java.data_gateway.CalendarManager;
import main.java.data_gateway.EventReader;
import main.java.services.event_creation.EventInfoFromReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventGetter implements CalendarEventDisplayBoundary {

    private final CalendarManager calendarManager;
    private final CalendarEventPresenter eventPresenter;

    public EventGetter(CalendarManager calendarManager, CalendarEventPresenter eventPresenter) {
        this.calendarManager = calendarManager;
        this.eventPresenter = eventPresenter;
    }

    /**
     * Get events from the database through data gateway and
     * pass the events as DTOs to the presenter to present all events.
     */

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

    public void presentAllEvents() {
        List<EventReader> calendarEvents = calendarManager.getAllEvents();
        List<EventInfo> eventInfos = new ArrayList<>();
        for (EventReader er : calendarEvents)
            eventInfos.add(new EventInfoFromReader(er));
        eventPresenter.presentAllEvents(eventInfos);
    }
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
