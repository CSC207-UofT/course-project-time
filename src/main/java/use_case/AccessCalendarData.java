package main.java.use_case;

import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AccessCalendarData {

    private Calendar calendar;

    /*
    Initializes the calendar and loads a list of sample events (for phase0)
     */
    public AccessCalendarData() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("Assignment 1",
                            LocalTime.parse("08:00"),
                            LocalTime.parse("09:00"),
                            new HashSet<String>(),
                            LocalDate.parse("2021-12-12")));
        events.add(new Event("Assignment 2",
                LocalTime.parse("08:00"),
                LocalTime.parse("09:00"),
                new HashSet<String>(),
                LocalDate.parse("2021-12-15")));
        calendar = new Calendar("My Calendar", events);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    /**
     *
     * @return a list of events in this calendar
     */
    public List<Event> getEvents() {
        return calendar.getEvents();
    }

    /**
     * Adds an event to the calendar and the associated task to the todolist associated with the calendar
     * @param event an event to be added to the calendar
     */
    public void addEvent(Event event) {
        calendar.getEvents().add(event);
        calendar.getTodoList().addTask(event.getTask());
    }

}
