package main.java.controllers;

import main.java.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class EventController {

    private ManageCalendarData data = new ManageCalendarData();
    private EventAdder eventAdder = new EventAdder();
    private GapFinder gapFinder = new SortAndSearch();
    private EventScheduler eventScheduler = new EventScheduler(gapFinder);

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public List<HashMap<String, String>> getEvents() {
        GetEvent eventGetter = new EventGetter(data);
        return eventGetter.getEvents();
    }

    public boolean createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                               HashSet<String> tags, LocalDate dates) {

        if(eventScheduler.isAvailable(startTime, Duration.between(startTime, endTime), dates, data))
        {
            eventAdder.addEvent(eventName, LocalDateTime.of(dates, startTime), LocalDateTime.of(dates, endTime), data);
        }
        return false;  // todo add body
    }



}
