package services.eventpresentation;

import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import services.eventcreation.EventInfoFromReader;

import java.util.ArrayList;
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
    public List<EventInfo> getEvents() {
        List<EventInfo> eventInfos = new ArrayList<>();
        for(EventReader eventReader : calendarManager.getAllEvents()) {
            eventInfos.add(new EventInfoFromReader(eventReader));
        }

        return eventInfos;
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
