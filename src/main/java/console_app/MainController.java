package main.java.console_app;

import main.java.console_app.event_adapters.ConsoleEventPresenter;
import main.java.console_app.event_adapters.EventController;
import main.java.console_app.task_adapters.ConsoleTaskPresenter;
import main.java.console_app.task_adapters.TaskController;
import main.java.console_app.task_to_event_adapters.TaskToEventController;
import main.java.data_gateway.CalendarManager;
import main.java.data_gateway.EventEntityManager;
import main.java.data_gateway.TodoEntityManager;
import main.java.data_gateway.TodoListManager;
import main.java.services.Snowflake;
import main.java.services.event_creation.CalendarEventCreationBoundary;
import main.java.services.event_creation.EventAdder;
import main.java.services.event_creation.EventSaver;
import main.java.services.event_from_task_creation.EventFromTaskCreator;
import main.java.services.event_from_task_creation.EventFromTaskCreatorBoundary;
import main.java.services.event_from_task_creation.EventScheduler;
import main.java.services.event_presentation.CalendarEventPresenter;
import main.java.services.event_presentation.EventGetter;
import main.java.services.task_creation.TaskAdder;
import main.java.services.task_creation.TodoListTaskCreationBoundary;
import main.java.services.task_creation.TaskSaver;
import main.java.services.task_presentation.TaskGetter;
import main.java.services.task_presentation.TaskInfo;
import main.java.services.task_presentation.TodoListPresenter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MainController {
    private final EventController eventController;
    private final TaskController taskController;
    private final TaskToEventController taskToEventController;
    private final Snowflake snowflake;
    private final PomodoroController pomodoroController;

    public MainController(ApplicationDriver applicationDriver) {

        snowflake = new Snowflake(0, 0, 0);

        CalendarManager calendarManager = new EventEntityManager(snowflake);
        CalendarEventCreationBoundary eventAdder = new EventAdder(calendarManager);
        EventScheduler eventScheduler = new EventScheduler(calendarManager);
        TodoListManager todoListManager = new TodoEntityManager(snowflake);

        try {
            calendarManager.loadEvents("EventData.json");
            todoListManager.loadTodo("TaskData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CalendarEventPresenter eventPresenter = new ConsoleEventPresenter(applicationDriver);
        EventGetter eventGetter = new EventGetter(calendarManager, eventPresenter);
        EventSaver eventSaver = new EventSaver(calendarManager);

        eventController = new EventController(eventAdder, eventScheduler, eventGetter,  eventSaver, snowflake);

        TodoListPresenter taskPresenter = new ConsoleTaskPresenter(applicationDriver);
        TaskGetter taskGetter = new TaskGetter(todoListManager, taskPresenter);
        TodoListTaskCreationBoundary taskAdder = new TaskAdder(todoListManager);
        TaskSaver taskSaver = new TaskSaver(todoListManager);
        taskController = new TaskController(taskGetter, taskAdder, taskSaver);

        EventFromTaskCreatorBoundary eventFromTaskCreator = new EventFromTaskCreator(todoListManager, calendarManager);
        taskToEventController = new TaskToEventController(eventController, eventFromTaskCreator, eventScheduler);
        pomodoroController = new PomodoroController();
    }

    /**
     * Displays all events.
     */
    public void presentAllEvents() {
        eventController.presentAllEvents();
    }

    /**
     * Displays all tasks.
     */
    public void presentAllTasks() {
        taskController.presentAllTasks();
    }

    /**
     * Gets a Task by its id
     * @param id id of a Task
     * @return TaskInfo of the corresponding Task
     */
    public TaskInfo getTaskById(Long id) {
        return taskController.getTaskById(id);
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

    public void saveData()
    {
        try {
            eventController.saveEvents("EventData.json");
            taskController.saveTodoList("TaskData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * create the pomodoro timer and start it, also stops the timer when the user specifies
     * @param workTime the time interval that the user specified they want to work for
     * @param breakTime the time interval that the user specified they want to break for
     */
    public void createAndEndTimer(int workTime, int breakTime) {
        pomodoroController.checkUserInput();
        pomodoroController.setPomodoroRunner(workTime, breakTime);
        boolean work = true;
        boolean switchInterval = true;
        while (switchInterval) {
            switchInterval = pomodoroController.startTimer();
            if (switchInterval) {
               if (work) {
                   System.out.println("Break time!");
                   work = false;
               }
               else {
                   System.out.println("Work time!");
                   work = true;
               }
            }
        }
        pomodoroController.stopTimer();
        System.out.println("Timer stopped");
    }

    /** Displays task information in a numbered list for user to select a task
     * for further actions.
     * @return a mapping of task's position in the presented list and id
     */
    public Map<Integer, Long> presentAllTasksForUserSelection() {
        return taskController.presentAllTasksForUserSelection();
    }
}
