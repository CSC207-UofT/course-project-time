package main.java.controllers;

import main.java.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class EventController {

    private ManageCalendarData data = new ManageCalendarData();
    // TODO: need an eventAdder

    /**
     * Returns a list containing mappings of event attributes
     * and their corresponding values
     */
    public List<HashMap<String, String>> getEvents() {
        GetEvent eventGetter = new EventGetter(data);
        return eventGetter.getEvents();
    }

    public boolean createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                               HashSet<String> tags, LocalDate date) {
        Event event = new Event(eventName, startTime, endTime, tags, date);
        return false;  // todo add body
    }

}
