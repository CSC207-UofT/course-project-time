package main.java.controllers;

import main.java.ManageCalendarData;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainController {
    EventController eventController = new EventController();
    TaskController taskController = new TaskController();

    /**
     * Return a list of events data in the format of a map, with keys as
     * "name", "start", and "end"
     */
    public List<HashMap<String, String>> getEvents() {
        return eventController.getEvents();
    }

    /**
     * Return a list of tasks data in the format of a map, with keys as
     * "name"
     */
    public List<HashMap<String, String>> getTasks() {
        return taskController.getTasks();
    }

    /**
     *
     * @param eventName name of the event to be created
     * @param startTime start time of the event
     * @param endTime   end time of the event
     * @param tags      a set of tags associated with the event
     * @param date      the date that the event would occur
     * @return whether the event is created
     */
    public boolean createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                               HashSet<String> tags, LocalDate date) {
        if(eventController.createEvent(eventName, startTime, endTime, tags, date))
        {
            taskController.createTask(eventName, Duration.between(startTime, endTime));
        }
        return true;
    }

    public boolean createTask(String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        return taskController.createTask(taskName, timeNeeded, deadline, subTasks);
    }

}
