package services.update_entities;

import data_gateway.CalendarManager;
import data_gateway.EventEntityManager;

import java.time.LocalTime;

public class UpdateEvent {
    CalendarManager calendarManager;
    long id;

    public UpdateEvent(EventEntityManager eventEntityManager, long id){
        this.calendarManager = eventEntityManager;
        this.id = id;
    }

    public void updateName(String newName){
        calendarManager.updateName(id, newName);
    }

    public void updateStartTime(LocalTime startTime){
        calendarManager.updateStartTime(id, startTime);
    }

    public void updateEndTime(LocalTime endTime){
        calendarManager.updateEndTime(id, endTime);
    }

    public void markEventAsCompleted() {
        calendarManager.markEventAsCompleted(id);
    }
}
