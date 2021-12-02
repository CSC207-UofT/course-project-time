package services.update_entities;

import data_gateway.CalendarManager;

import java.time.LocalTime;

public class EventUpdater implements UpdateEventBoundary{
    CalendarManager calendarManager;

    public EventUpdater(CalendarManager eventEntityManager){
        this.calendarManager = eventEntityManager;
    }


    @Override
    public void updateName(long id, String newName) {
        calendarManager.updateName(id, newName);
    }

    @Override
    public void updateStartTime(long id, LocalTime newStartTime) {
        calendarManager.updateStartTime(id, newStartTime);
    }

    @Override
    public void updateEndTime(long id, LocalTime newEndTime) {
        calendarManager.updateEndTime(id, newEndTime);
    }

    @Override
    public void addTag(long id, String tag) {
        calendarManager.addTag(id, tag);
    }

    @Override
    public void removeTag(long id, String tag) {
        calendarManager.removeTag(id, tag);
    }

    @Override
    public void markEventAsCompleted(long id) {
        calendarManager.markEventAsCompleted(id);
    }
}