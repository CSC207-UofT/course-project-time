package main.java.services.event_presentation;

import main.java.data_gateway.CalendarManager;
import main.java.data_gateway.EventReader;
import main.java.services.event_creation.EventInfoFromReader;

import java.util.ArrayList;
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
    @Override
    public void presentAllEvents() {
        List<EventReader> calendarEvents = calendarManager.getAllEvents();
        List<EventInfo> eventInfos = new ArrayList<>();
        for (EventReader er : calendarEvents)
            eventInfos.add(new EventInfoFromReader(er));
        eventPresenter.presentAllEvents(eventInfos);
    }
}
