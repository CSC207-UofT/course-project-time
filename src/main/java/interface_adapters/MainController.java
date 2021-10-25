package main.java.interface_adapters;

import main.java.entity.Task;
import main.java.entity_gateway.TodoListEntityManager;
import main.java.use_case.AccessTodoData;
import main.java.use_case.TaskAdder;
import main.java.use_case.TaskGetter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainController {
    private final EventController eventController;
    private final TaskController taskController;
    private final TaskToEventController taskToEventController;

    public MainController() {
        eventController = new EventController();

        AccessTodoData accessTodoData = new AccessTodoData();
        TaskGetter taskGetter = new TaskGetter();
        TaskAdder taskAdder = new TaskAdder(new TodoListEntityManager());
        taskController = new TaskController(accessTodoData, taskGetter, taskAdder);

        taskToEventController = new TaskToEventController(eventController);
    }

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
     * Gets a Task by its name
     * @param name name of Task
     * @return Task with given name
     */
    public Task getTaskByName(String name) {
        return taskController.getTaskByName(name);
    }

    /**
     * creates an event and adds it to the calendar
     * @param eventName name of the event to be created
     * @param startTime start time of the event
     * @param endTime   end time of the event
     * @param tags      a set of tags associated with the event
     * @param date      the date that the event would occur
     * @return whether the event is created
     */
    public boolean createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                               HashSet<String> tags, LocalDate date) {
        return eventController.createEvent(eventName, startTime, endTime, tags, date);
    }
    /**
     * creates a task and adds it to the todolist
     */
    public boolean createTask(String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        return taskController.createTask(taskName, timeNeeded, deadline, subTasks);
    }

    /**
     * Suggest a time to the user until the user is agrees with the time
     * @param task the task to be scheduled to event
     * @return whether the task is successfully scheduled to event
     */
    public boolean suggestTimeToUser(Task task) {
        return taskToEventController.suggestTimeToUser(task);
    }

    /**
     * Check whether the time suggested by the user is available
     * @param task the task to be scheduled to event
     * @param userSuggestedTime the time suggested by the user
     * @return whether the task is successfully scheduled to event
     */
    public boolean checkUserSuggestedTime(Task task, LocalDateTime userSuggestedTime) {
        return taskToEventController.checkUserSuggestedTime(task, userSuggestedTime);
    }

}
