package main.java.use_case;

import main.java.entity.Event;
import main.java.entity.Task;
import main.java.entity.TodoList;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EventAdder {

    /**
     * Create a new event and add it to the calendar
     * Add the corresponding task to the todoList
     * @param eventName the name of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @return false if the event has conflict with calendar;
     *         true if the event is created and added to calendar successfully
     */
    public boolean addEvent(String eventName, LocalDateTime startTime,
                            LocalDateTime endTime,
                            AccessCalendarData calendarData) {
        Duration timeNeeded = Duration.between(startTime, endTime);
        Task task = new Task(eventName, timeNeeded);
        Event event = new Event(task, startTime, endTime.toLocalTime());
        calendarData.addEvent(event);
        return true;
    }

    /**
     * Create a repeated event and add it to the calendar
     * Add the corresponding task to the todoList
     * @param eventName the name of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param dates repeated dates of the event
     * @param todoList todoList for tasks
     * @param eventScheduler the event scheduler that stores all events
     * @return false if the event has conflict with calendar;
     *         true if the event is created and added to calendar successfully
     */
    // TODO implement make this method add to ManageCalendarData
    public boolean addRepeatedEvents(String eventName, LocalDateTime startTime,
                                     LocalDateTime endTime, List<LocalDate> dates,
                                     TodoList todoList, EventScheduler eventScheduler) {
        Duration timeNeeded = Duration.between(startTime, endTime);
        Task task = new Task(eventName, timeNeeded);
        todoList.addTask(task);
        Event event = new Event(task, startTime, endTime.toLocalTime());
        event.addRepeatedDates(dates);
        return true;
    }
}
