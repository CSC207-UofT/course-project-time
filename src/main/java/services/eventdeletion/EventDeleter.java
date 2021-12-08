package services.eventdeletion;

import datagateway.event.CalendarManager;

public class EventDeleter implements EventDeletionBoundary {

    private final CalendarManager calendarManager;

    public EventDeleter(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public void deleteEvent(long eventId) {
        calendarManager.deleteEvent(eventId);
    }
}
