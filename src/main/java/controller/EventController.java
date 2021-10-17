package main.java.controller;

import main.java.use_case.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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
