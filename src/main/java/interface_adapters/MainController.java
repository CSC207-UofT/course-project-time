package main.java.interface_adapters;

import main.java.entity_gateway.CalendarManager;
import main.java.entity_gateway.EventEntityManager;
import main.java.entity_gateway.TodoEntityManager;
import main.java.entity_gateway.TodoListManager;
import main.java.use_case.CalendarEventCreationBoundary;
import main.java.use_case.CalendarEventPresenter;
import main.java.use_case.EventAdder;
import main.java.use_case.EventFromTaskCreator;
import main.java.use_case.EventFromTaskCreatorBoundary;
import main.java.use_case.EventGetter;
import main.java.use_case.EventScheduler;
import main.java.use_case.TaskAdder;
import main.java.use_case.TaskGetter;
import main.java.use_case.TaskInfo;
import main.java.use_case.TodoListPresenter;
import main.java.use_case.TodoListTaskCreationBoundary;
import main.java.use_case.TodoListsInfo;

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
    private final PomodoroController pomodoroController;

    public MainController() {

        CalendarManager calendarManager = new EventEntityManager();
        CalendarEventCreationBoundary eventAdder = new EventAdder(calendarManager);
        EventScheduler eventScheduler = new EventScheduler(calendarManager);

        CalendarEventPresenter eventPresenter = new ConsoleEventPresenter();
        EventGetter eventGetter = new EventGetter(calendarManager, eventPresenter);

        eventController = new EventController(eventAdder, eventScheduler, eventGetter);

        TodoListPresenter taskPresenter = new ConsoleTaskPresenter();
        TodoListManager todoListManager = new TodoEntityManager();
        TaskGetter taskGetter = new TaskGetter(todoListManager, taskPresenter);
        TodoListTaskCreationBoundary taskAdder = new TaskAdder(todoListManager);
        taskController = new TaskController( taskGetter, taskAdder);

        EventFromTaskCreatorBoundary eventFromTaskCreator = new EventFromTaskCreator(todoListManager, calendarManager);
        taskToEventController = new TaskToEventController(eventController, eventFromTaskCreator);

        pomodoroController = new PomodoroController();
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
    public TodoListsInfo getTasks() {
        return taskController.getTasks();
    }

    /**
     * Gets a Task by its name
     * @param name name of Task
     * @return TaskInfo with given name
     */
    public TaskInfo getTaskByName(String name) {
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
    public boolean suggestTimeToUser(TaskInfo task) {
        return taskToEventController.suggestTimeToUser(task);
    }

    /**
     * Check whether the time suggested by the user is available
     * @param task the task to be scheduled to event
     * @param userSuggestedTime the time suggested by the user
     * @return whether the task is successfully scheduled to event
     */
    public boolean checkUserSuggestedTime(TaskInfo task, LocalDateTime userSuggestedTime) {
        return taskToEventController.checkUserSuggestedTime(task, userSuggestedTime);
    }

    /**
     * create the pomodoro timer and start it
     * @param workTime the time interval that the user specified they want to work for
     * @param breakTime the time interval that the user specified they want to break for
     */
    public void createTimer(int workTime, int breakTime) {
        pomodoroController.setPomodoroRunner(workTime, breakTime);
        pomodoroController.startTimer();
    }

    public boolean stopTimer() {
        return pomodoroController.stopTimer();
    }

}
