package main.java.controllers;

import main.java.*;
import main.java.Calendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class EventController {

    private static Calendar calendar = new Calendar("calendar");
    private static final GetEvent eventGetter = new EventGetter(calendar);
    // TODO: need an eventAdder

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public List<HashMap<String, String>> getEvents() {
        return eventGetter.getEvents();
    }

    public boolean createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                               HashSet<String> tags, LocalDate date) {
        Event event = new Event(eventName, startTime, endTime, tags, date);
        return false;  // todo add body
    }

}
