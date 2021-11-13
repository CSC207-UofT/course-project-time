package main.java.use_case;

import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.EventReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventGetter implements GetEvent, CalendarEventDisplayBoundary {
    Calendar calendar;

    private final CalendarManager calendarManager;
    private final CalendarEventPresenter eventPresenter;

    public EventGetter(CalendarManager calendarManager, CalendarEventPresenter eventPresenter) {
        this.calendarManager = calendarManager;
        this.eventPresenter = eventPresenter;
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

    @Override
    public void presentCalendar() {
        List<EventReader> calendarEvents = calendarManager.getAllEvents();
        List<EventInfo> eventInfos = new ArrayList<>();
        for (EventReader er : calendarEvents)
            eventInfos.add(new EventInfoFromReader(er));
        eventPresenter.presentEvents(eventInfos);
    }
}