package main.java;

import java.util.ArrayList;
import java.util.List;

public class ManageCalendarData {

    private Calendar calendar;
    private TodoList todoList;

    /*
    Initalizes the calendar and Todolist for the project,
    and loads a sample sleep schedule.
     */
    public ManageCalendarData()
    {
        // A sample sleep schedule could be added here
        Event[] events = new Event[0];
        calendar = new Calendar("My Calendar", events);
        todoList = new TodoList();
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void updateCalendar(Calendar calendar)
    {
        this.calendar = calendar;
    }
}
