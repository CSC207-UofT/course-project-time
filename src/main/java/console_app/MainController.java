package console_app;

import console_app.event_adapters.ConsoleEventPresenter;
import console_app.event_adapters.EventController;
import console_app.task_adapters.ConsoleTaskPresenter;
import console_app.task_adapters.TaskController;
import console_app.task_to_event_adapters.TaskToEventController;
import data_gateway.event.CalendarManager;
import data_gateway.event.EventEntityManager;
import data_gateway.event.ObservableEventRepository;
import data_gateway.task.TodoEntityManager;
import data_gateway.task.TodoListManager;
import data_gateway.event.ObservableEventEntityManager;
import data_gateway.task.ObservableTaskEntityManager;
import data_gateway.task.ObservableTaskManager;
import services.Snowflake;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventAdder;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_from_task_creation.EventScheduler;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.EventGetter;
import services.event_presentation.EventInfo;
import services.strategy_building.DatesForm;
import services.task_creation.TaskAdder;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskGetter;
import services.task_presentation.TaskInfo;
import services.task_presentation.TaskOutputter;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListPresenter;
import services.update_entities.EventUpdater;
import services.update_entities.TaskUpdater;
import services.task_presentation.TodoListRequestBoundary;

import java.io.IOException;
import java.time.Duration;
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
        ObservableEventRepository observableEventManager = new ObservableEventEntityManager(calendarManager);
        observableEventManager.addCreationObserver(
                (eventReader) -> System.out.println("New event \"" + eventReader.getName() + "\" was created"));
        observableEventManager.addUpdateObserver(
                (eventReader) -> System.out.println(eventReader.getName() + "was updated!"));
        CalendarEventCreationBoundary eventAdder = new EventAdder(observableEventManager);
        CalendarAnalyzer eventScheduler = new EventScheduler(observableEventManager);

        TodoListManager todoListManager = new TodoEntityManager(snowflake);
        ObservableTaskManager observableTaskManager = new ObservableTaskEntityManager(todoListManager);
        observableTaskManager.addCreationObserver(
                (taskReader) -> System.out.println("New task \"" + taskReader.getName() + "\" was created"));
        observableTaskManager.addUpdateObserver(
                (taskReader) -> System.out.println(taskReader.getName() + "was updated"));

        try {
            observableEventManager.loadEvents("EventData.json");
            observableTaskManager.loadTodo("TaskData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CalendarEventPresenter eventPresenter = new ConsoleEventPresenter(applicationDriver);
        EventGetter eventGetter = new EventGetter(observableEventManager, eventPresenter);
        EventSaver eventSaver = new EventSaver(observableEventManager);
        EventUpdater eventUpdater = new EventUpdater(observableEventManager);
        eventController = new EventController(eventAdder, eventGetter, eventSaver, eventUpdater);
        TodoListPresenter taskPresenter = new ConsoleTaskPresenter(applicationDriver);
        TodoListRequestBoundary taskGetter = new TaskGetter(observableTaskManager);
        TodoListDisplayBoundary taskOutputter = new TaskOutputter(observableTaskManager, taskPresenter);
        TodoListTaskCreationBoundary taskAdder = new TaskAdder(observableTaskManager);
        TaskSaver taskSaver = new TaskSaver(observableTaskManager);
        TaskUpdater taskUpdater = new TaskUpdater(observableTaskManager);
        taskController = new TaskController(taskGetter, taskOutputter, taskAdder, taskSaver, taskUpdater);
        taskToEventController = new TaskToEventController(eventScheduler);
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
