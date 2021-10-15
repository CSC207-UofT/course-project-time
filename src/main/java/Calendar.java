package main.java;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Immutable Entity storing many events and some methods to query them.
 */
public class Calendar {

    private final String name;
    private final Event[] events;
    private final TodoList todoList;

    public Calendar(String name, Event[] events) {
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

    public Event[] getEvents() { return events.clone(); }

}
