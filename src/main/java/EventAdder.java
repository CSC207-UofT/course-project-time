package main.java;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public class EventAdder {

    /**
     * Create a new event and add it to the calendar
     * @param eventName the name of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param eventScheduler the event scheduler that stores all events
     * @return false if the event has conflict with calendar;
     *         true if the event is created and added to calendar successfully
     */
    public boolean addEvent(String eventName, LocalDateTime startTime,
                            LocalDateTime endTime, EventScheduler eventScheduler) {
        Duration timeNeeded = Duration.between(startTime, endTime);
        Task task = new Task(eventName, timeNeeded);
        Event event = new Event(task, startTime, endTime.toLocalTime());
        return eventScheduler.addEvent(event);
    }
}
