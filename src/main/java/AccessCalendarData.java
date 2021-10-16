package main.java;

import java.util.ArrayList;
import java.util.List;

public class AccessCalendarData {

    private Calendar calendar;
    private TodoList todoList;

    /*
    Initalizes the calendar and Todolist for the project,
    and loads a sample sleep schedule.
     */
    public AccessCalendarData()
    {
        // A sample sleep schedule could be added here
        ArrayList<Event> events = new ArrayList<>();
        calendar = new Calendar("My Calendar", events);
        todoList = new TodoList();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    /**
     *
     * @param event an event to add to the calendar
     */
    public void addEvent(Event event)
    {
        calendar.getEvents().add(event);
    }


    public TodoList getTodoList() {
        return todoList;
    }

    public void updateCalendar(Calendar calendar)
    {
        this.calendar = calendar;
    }
}
