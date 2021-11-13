package main.java.services.event_presentation;

import main.java.data_gateway.CalendarManager;
import main.java.data_gateway.EventReader;
import main.java.services.event_creation.EventInfoFromReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventGetter implements GetEvent, CalendarEventDisplayBoundary {

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
        for(EventReader eventReader : this.calendarManager.getAllEvents()) {
            event_data.add(getEvent(eventReader));
        }

        return event_data;
    }

    private HashMap<String, String> getEvent(EventReader eventReader) {
        HashMap<String, String> event_data = new HashMap<>();
        event_data.put("name", eventReader.getName());
        event_data.put("start", eventReader.getStartTime().toString());
        event_data.put("end", eventReader.getEndTime().toString());
        event_data.put("tags", eventReader.getTags().toString());
        event_data.put("dates", eventReader.getDates().toString());

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
