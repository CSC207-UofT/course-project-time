package entity;

import java.util.List;

/**
 * Immutable Entity storing many events and some methods to query them.
 */
public class Calendar {

    private final String name;

    public Calendar(String name, List<Event> events) {
        this.name = name;
        TodoList todoList = new TodoList();

        for(Event event : events) {
            Task task = new Task(event.getId(), event.getEventName());
            todoList.addTask(task);
        }
    }


    /**
     * @return the name of this calendar
     */
    public String getName() {
        return name;
    }

}
