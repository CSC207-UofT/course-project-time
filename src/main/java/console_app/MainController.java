package console_app;

import console_app.event_adapters.ConsoleEventPresenter;
import console_app.event_adapters.EventController;
import console_app.task_adapters.ConsoleTaskPresenter;
import console_app.task_adapters.TaskController;
import console_app.task_to_event_adapters.TaskToEventController;
import data_gateway.CalendarManager;
import data_gateway.EventEntityManager;
import data_gateway.TodoEntityManager;
import data_gateway.TodoListManager;
import services.Snowflake;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventAdder;
import services.event_creation.EventSaver;
import services.event_from_task_creation.EventScheduler;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.EventGetter;
import services.event_presentation.EventInfo;
import services.task_creation.TaskAdder;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_creation.TaskSaver;
import services.task_presentation.TaskGetter;
import services.task_presentation.TaskInfo;
import services.task_presentation.TodoListPresenter;
import services.update_entities.EventUpdater;
import services.update_entities.TaskUpdater;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MainController {
    private final EventController eventController;
    private final TaskController taskController;
    private final TaskToEventController taskToEventController;
    private final PomodoroController pomodoroController;

    public MainController(ApplicationDriver applicationDriver) {

        Snowflake snowflake = new Snowflake(0, 0, 0);

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
        EventUpdater eventUpdater = new EventUpdater(calendarManager);

        eventController = new EventController(eventAdder, eventScheduler, eventGetter,  eventSaver, eventUpdater);

        TodoListPresenter taskPresenter = new ConsoleTaskPresenter(applicationDriver);
        TaskGetter taskGetter = new TaskGetter(todoListManager, taskPresenter);
        TodoListTaskCreationBoundary taskAdder = new TaskAdder(todoListManager);
        TaskSaver taskSaver = new TaskSaver(todoListManager);
        TaskUpdater taskUpdater = new TaskUpdater(todoListManager);
        taskController = new TaskController(taskGetter, taskAdder, taskSaver, taskUpdater);

        taskToEventController = new TaskToEventController(eventController, eventScheduler);
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
     * Gets an Event by its name
     * @param name name of Event
     * @return EventInfo with given name
     */
    public EventInfo getEventByName(String name) {
        return eventController.getEventByName(name);
    }
    /**
     * creates an event and adds it to the calendar
     * @param eventName name of the event to be created
     * @param startTime start time of the event
     * @param endTime   end time of the event
     * @param tags      a set of tags associated with the event
     * @param date      the date that the event would occur
     */
    public void createEvent(String eventName, LocalTime startTime, LocalTime endTime,
                            HashSet<String> tags, LocalDate date) {
        eventController.createEvent(eventName, startTime, endTime, tags, date);
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
     * @param id the id of Task
     * @param newName new name of Task
     */
    public void updateTaskName(long id, String newName){
        taskController.updateName(id, newName);
    }

    /**
     * sets completed attribute as true for the selected Task
     * @param id the id of Task
     * @param newDuration new duration of Task
     */
    public void updateTaskDuration(long id, Duration newDuration){
        taskController.updateDuration(id, newDuration);
    }

    /**
     * sets completed attribute as true for the selected Task
     * @param id the id of Task
     * @param newDeadline new deadline of Task
     */
    public void updateTaskDeadline(long id, LocalDateTime newDeadline){
        taskController.updateDeadline(id, newDeadline);
    }

    /**
     * sets completed attribute as true for the selected Task
     * @param id the id of Task
     * @param subtask new subtask to add to Task
     */
    public void addSubtask(long id, String subtask){
        taskController.addSubtask(id, subtask);
    }

    /**
     * sets completed attribute as true for the selected Task
     * @param id the id of Task
     * @param subtask subtask to remove from Task
     */
    public void removeSubtask(long id, String subtask){
        taskController.removeSubtask(id, subtask);
    }

    /**
     * sets completed attribute as true for the selected Task
     * @param id the id of the completed Task
     */
    public void completeTask(long id) {
        taskController.completeTask(id);
    }

    public void updateEventName(long id, String newName){eventController.updateName(id, newName);}

    public void updateEventStartTime(long id, LocalTime newStartTime){eventController.updateStartTime(id, newStartTime);}

    public void updateEventEndTime(long id, LocalTime newEndTime){eventController.updateEndTime(id, newEndTime);}

    public void addTag(long id, String tag){eventController.addTag(id, tag);}

    public void removeTag(long id, String tag){eventController.removeTag(id, tag);}

    /**
     * sets completed attribute as true for the selected Event
     * @param eventId the id of the completed Event
     */
    public void completeEvent(long eventId) {
        eventController.markEventAsCompleted(eventId);
    }

    /**
     * Suggest a time to the user until the user is agrees with the time
     * @param task the task to be scheduled to event
     */
    public void suggestTimeToUser(TaskInfo task) {
        taskToEventController.suggestTimeToUser(task);
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

    public List<HashMap<String, String >> getEvents() {
        return eventController.getEvents();
    }
}
