package services.event_presentation;

import data_gateway.CalendarManager;
import data_gateway.EventReader;
import services.event_creation.EventInfoFromReader;

import java.util.ArrayList;
import java.util.List;

public class EventOutputter implements CalendarEventDisplayBoundary {

    private final CalendarManager calendarManager;
    private final CalendarEventPresenter eventPresenter;

    public EventOutputter(CalendarManager calendarManager, CalendarEventPresenter eventPresenter) {
        this.calendarManager = calendarManager;
        this.eventPresenter = eventPresenter;
    }

    @Override
    public void presentAllEvents() {
        List<EventReader> calendarEvents = calendarManager.getAllEvents();
        List<EventInfo> eventInfos = new ArrayList<>();
        for (EventReader er : calendarEvents)
            eventInfos.add(new EventInfoFromReader(er));
        eventPresenter.presentAllEvents(eventInfos);
    }
}
