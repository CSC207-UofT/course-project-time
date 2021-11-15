package main.java.services.rescheduling;

import main.java.data_gateway.CalendarManager;
import main.java.services.event_from_task_creation.EventScheduler;
import main.java.services.event_presentation.EventInfo;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Rescheduler implements ReschedulerAuto {
    private final CalendarManager calendarManager;

    public Rescheduler(CalendarManager calendarManager){
        this.calendarManager = calendarManager;
    }

    public Duration getDuration(EventInfo eventInfo){
        return Duration.between(eventInfo.getStartTime(), eventInfo.getEndTime());
    }

    /**
     * Checks the availability of the requested time
     * @param eventInfo the event that is being rescheduled
     * @param eventScheduler what will be used for this scheduling
     * @param userRequestedTime times that the user suggested
     * @return whether the time suggested by the user is available
     */
    public boolean checkRequestedTime(EventInfo eventInfo, EventScheduler eventScheduler, LocalDateTime userRequestedTime){
        return eventScheduler.isAvailable(userRequestedTime.toLocalTime(), getDuration(eventInfo), userRequestedTime.toLocalDate());
    }

    /**
     * Gets available time to reschedule events
     * @param eventInfo the event that is being rescheduled
     * @param eventScheduler what will be used for this scheduling
     * @param timesToIgnore time that the user does not want
     * @return the time outputted by the eventScheduler
     */
    @Override
    public LocalDateTime getAvailableTime(EventInfo eventInfo, EventScheduler eventScheduler, List<LocalDateTime> timesToIgnore) {
        return eventScheduler.getAvailableTime(timesToIgnore, getDuration(eventInfo));
    }

    /**
     * Update the specified events startTime and endTime
     * @param eventId the id of the specific event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @return whether it has been successfully updated
     */
    public boolean update(Long eventId, LocalDateTime startTime, LocalDateTime endTime){
        return calendarManager.rescheduleEvent(eventId, startTime, endTime);
    }
}