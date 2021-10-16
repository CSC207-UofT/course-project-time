package main.java;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Immutable Entity storing many events and some methods to query them.
 */
public class Calendar {

    private final String name;
    private ArrayList<Event> events;
    private TodoList todoList;

    public Calendar(String name, ArrayList<Event> events) {
        this.name = name;
        this.events = events;
        this.todoList = new TodoList();

        for(Event event : this.events)
        {
            Task task = new Task(event.getEventName());
            todoList.addTask(task);
        }
    }


    /**
     * @return the name of this calendar. The name has no functional purpose.
     */
    public String getName() {
        return name;
    }

    public ArrayList<Event> getEvents() { return events; }

}

class TimeFrame implements Comparable<TimeFrame> {
    public final LocalDateTime start;
    public final LocalDateTime end;
    public TimeFrame(LocalDateTime start, LocalDateTime end) {
         this.start = start;
         this.end = end;
    }
    @Override
    public int compareTo(TimeFrame o) {
        return this.start.compareTo(o.start);
    }
}

