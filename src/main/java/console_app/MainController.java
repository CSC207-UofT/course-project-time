package console_app;

import console_app.event_adapters.ConsoleEventPresenter;
import console_app.event_adapters.EventController;
import console_app.task_adapters.ConsoleTaskPresenter;
import console_app.task_adapters.TaskController;
import console_app.task_to_event_adapters.TaskToEventController;
import services.event_presentation.EventInfo;
import services.strategy_building.DatesForm;
import services.task_presentation.TaskInfo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MainController {
    private final EventController eventController;
    private final TaskController taskController;
    private final TaskToEventController taskToEventController;
    private final PomodoroController pomodoroController;

    public MainController(ApplicationDriver applicationDriver, ConsoleAppFactory consoleAppFactory) {
        eventController = consoleAppFactory.makeEventController(new ConsoleEventPresenter(applicationDriver));
        taskController = consoleAppFactory.makeTaskController(new ConsoleTaskPresenter(applicationDriver));
        taskToEventController = consoleAppFactory.makeTaskToEventController();
        pomodoroController = consoleAppFactory.makePomodoroController();
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
     * Gets an Event by its name
     * @param name name of Event
     * @return EventInfo with given name
     */
    public EventInfo getEventByName(String name) {
        return eventController.getEventByName(name);
    }
    /**
     * @see console_app.event_adapters.EventController#createEvent(String, Duration, DatesForm, HashSet)
     */
    public void createEvent(String eventName, Duration duration, DatesForm form, HashSet<String> tags) {
        eventController.createEvent(eventName, duration, form, tags);
    }

    /**
     * @see console_app.event_adapters.EventController#createEvent(String, Duration, DatesForm)
     */
    public void createEvent(String eventName, Duration duration, DatesForm form) {
        eventController.createEvent(eventName, duration, form);
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
    public void createTask(String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
         taskController.createTask(taskName, timeNeeded, deadline, subTasks);
    }

    /**
     * sets completed attribute as true for the selected Task
     * @param taskId the id of the completed Task
     */
    public void completeTask(long taskId) {
        taskController.completeTask(taskId);
    }

    /**
     * sets completed attribute as true for the selected Event
     * @param eventId the id of the completed Event
     */
    public void completeEvent(long eventId) {
        eventController.markEventAsCompleted(eventId);
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

    public LocalDateTime getSuggestedTime(Duration duration) {
        return taskToEventController.getSuggestedTime(duration);
    }

    public List<HashMap<String, String >> getEvents() {
        return eventController.getEvents();
    }
}
