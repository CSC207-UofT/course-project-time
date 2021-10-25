package main.java.interface_adapters;


import main.java.use_case.AccessCalendarData;
import main.java.use_case.EventAdder;
import main.java.use_case.EventScheduler;
import main.java.use_case.GetEvent;
import main.java.use_case.EventGetter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

public class EventController {

    protected final AccessCalendarData calendarData = new AccessCalendarData();
    protected final EventAdder eventAdder = new EventAdder();
    protected final EventScheduler eventScheduler = new EventScheduler();

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public List<HashMap<String, String>> getEvents() {
        GetEvent eventGetter = new EventGetter(calendarData);
        return eventGetter.getEvents();
    }

    /**
     * checks whether the time period is available to schedule a new event
     * and add the event if it is available
     * @param eventName name of event
     * @param startDateTime start time including date
     * @param duration duration of event
     * @return whether the creation succeeded
     */
    public boolean createEvent(String eventName, LocalDateTime startDateTime, Duration duration) {
        // todo use exceptions to ensure that duration won't last until the next day
        LocalTime endTime = startDateTime.plus(duration.getSeconds(), ChronoUnit.SECONDS).toLocalTime();
        return createEvent(eventName, startDateTime.toLocalTime(), endTime, new HashSet<String>(), startDateTime.toLocalDate());
    }

    /**
     * checks whether the time period is available to schedule a new event
     * and add the event if it is available
     * @param eventName name of event
     * @param startTime start time of event
     * @param endTime end time of event
     * @param tags relevant tags of event
     * @param dates date of which this event occurs
     * @return whether the event has been created successfully
     */
    public boolean createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                               HashSet<String> tags, LocalDate dates) {

        if(eventScheduler.isAvailable(startTime, Duration.between(startTime, endTime), dates, calendarData)) {
            return eventAdder.addEvent(eventName,
                                        LocalDateTime.of(dates, startTime),
                                        LocalDateTime.of(dates, endTime),
                                        tags, dates, calendarData);
        }
        return false;
    }

}
