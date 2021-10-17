package main.java.controller;


import main.java.use_case.AccessCalendarData;
import main.java.use_case.EventAdder;
import main.java.use_case.EventScheduler;
import main.java.use_case.GetEvent;
import main.java.use_case.EventGetter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;

public class EventController {

    private final AccessCalendarData calendarData = new AccessCalendarData();
    private final EventAdder eventAdder = new EventAdder();
    private final EventScheduler eventScheduler = new EventScheduler();

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
