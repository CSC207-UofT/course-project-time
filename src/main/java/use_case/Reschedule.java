package main.java.use_case;

import main.java.entity_gateway.CalendarManager;
import java.time.LocalDateTime;
import java.util.List;

public class Reschedule implements RescheduleAuto{
    private final CalendarManager calendarManager;

    public Reschedule(CalendarManager calendarManager){
        this.calendarManager = calendarManager;
    }

    public boolean checkRequestedTime(TaskInfo taskInfo, EventScheduler eventScheduler, LocalDateTime userRequestedTime){
        return eventScheduler.isAvailable(userRequestedTime.toLocalTime(), taskInfo.getDuration(), userRequestedTime.toLocalDate());
    }

    @Override
    public LocalDateTime getAvailableTime(TaskInfo taskInfo, EventScheduler eventScheduler, List<LocalDateTime> timesToIgnore) {
        return eventScheduler.getAvailableTime(timesToIgnore, taskInfo.getDuration());
    }

    public boolean update(String eventName, LocalDateTime startTime, LocalDateTime endTime){
        return calendarManager.update(eventName, startTime, endTime);
    }
}