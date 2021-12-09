package consoleapp;


import consoleapp.eventadapters.EventController;
import consoleapp.taskadapters.ConsoleTaskPresenter;
import consoleapp.taskadapters.TaskController;
import consoleapp.tasktoeventadapters.TaskToEventController;
import services.eventcreation.CalendarEventCreationBoundary;
import services.eventcreation.EventSaver;
import services.eventdeletion.EventDeletionBoundary;
import services.eventfromtaskcreation.CalendarAnalyzer;
import services.eventpresentation.CalendarEventDisplayBoundary;
import services.eventpresentation.CalendarEventPresenter;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.servicesfactory.ServicesFactory;
import services.taskcreation.TaskSaver;
import services.taskcreation.TodoListTaskCreationBoundary;
import services.taskdeletion.TaskDeletionBoundary;
import services.taskpresentation.TodoListDisplayBoundary;
import services.taskpresentation.TodoListRequestBoundary;
import services.updateentities.UpdateEventBoundary;
import services.updateentities.UpdateTaskBoundary;

public class ConsoleAppFactory {

    private final ServicesFactory servicesFactory;

    private TaskController cachedTaskController;
    private EventController cachedEventController;
    private TaskToEventController cachedTaskToEventController;

    private OldPomodoroController cachedOldPomodoroController;

    public ConsoleAppFactory(ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
    }

    public TaskController makeTaskController(ConsoleTaskPresenter taskPresenter) {
        if (cachedTaskController == null) {
            TodoListDisplayBoundary taskOutputter = servicesFactory.makeTaskOutputter(taskPresenter);
            TodoListRequestBoundary taskGetter = servicesFactory.makeTaskGetter();
            TodoListTaskCreationBoundary taskCreator = servicesFactory.makeTaskCreator();
            TaskSaver taskSaver = servicesFactory.makeTaskSaver();
            UpdateTaskBoundary taskUpdater = servicesFactory.makeTaskUpdater();
            TaskDeletionBoundary taskDeleter = servicesFactory.makeTaskDeleter();
            cachedTaskController = new TaskController(taskGetter, taskOutputter, taskCreator, taskSaver, taskUpdater, taskDeleter);
        }
        return cachedTaskController;
    }

    public EventController makeEventController(CalendarEventPresenter eventPresenter) {
        if (cachedEventController == null ) {
            CalendarEventCreationBoundary eventCreator = servicesFactory.makeEventCreator();
            CalendarEventDisplayBoundary eventOutputter = servicesFactory.makeEventOutputter(eventPresenter);
            CalendarEventRequestBoundary eventGetter = servicesFactory.makeEventGetter();
            EventSaver eventSaver = servicesFactory.makeEventSaver();
            UpdateEventBoundary eventUpdater = servicesFactory.makeEventUpdater();
            EventDeletionBoundary eventDeleter = servicesFactory.makeEventDeleter();
            cachedEventController = new EventController(eventCreator, eventGetter, eventOutputter, eventSaver,
                    eventUpdater, eventDeleter);
        }
        return cachedEventController;
    }

    public TaskToEventController makeTaskToEventController() {
        if (cachedTaskToEventController == null) {
            CalendarAnalyzer calendarAnalyzer = servicesFactory.makeCalendarAnalyzer();
            cachedTaskToEventController = new TaskToEventController(calendarAnalyzer);
        }
        return cachedTaskToEventController;

    }

    public OldPomodoroController makePomodoroController() {
        if (cachedOldPomodoroController == null)
            cachedOldPomodoroController = new OldPomodoroController();
        return cachedOldPomodoroController;
    }

}
