package services.eventpresentation;

import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import services.eventcreation.EventInfoFromReader;

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
