package main.java;

import java.util.List;

/**
 * Immutable Entity storing many events and some methods to query them.
 */
public class Calendar {

    private final String name;
    private final List<Event> events;
    private final TodoList todoList;

    public Calendar(String name, List<Event> events) {
        this.name = name;
        this.events = events;
        this.todoList = new TodoList();

        for(Event event : this.events) {
            Task task = new Task(event.getEventName());
            todoList.addTask(task);
        }
    }


    /**
     * @return the name of this calendar
     */
    public String getName() {
        return name;
    }

    public List<Event> getEvents() { return events; }

}
