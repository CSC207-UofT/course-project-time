package console_app;


import console_app.event_adapters.EventController;
import console_app.task_adapters.ConsoleTaskPresenter;
import console_app.task_adapters.TaskController;
import console_app.task_to_event_adapters.TaskToEventController;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.EventGetter;
import services.services_factory.ServicesFactory;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskGetter;

public class ConsoleAppFactory {

    private final ServicesFactory servicesFactory;

    private TaskController cachedTaskController;
    private EventController cachedEventController;
    private TaskToEventController cachedTaskToEventController;

    private PomodoroController cachedPomodoroController;

    public ConsoleAppFactory(ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
    }

    public TaskController makeTaskController(ConsoleTaskPresenter taskPresenter) {
        if (cachedTaskController == null) {
            TaskGetter taskGetter = servicesFactory.makeTaskOutputter(taskPresenter);
            TodoListTaskCreationBoundary taskCreator = servicesFactory.makeTaskCreator();
            TaskSaver taskSaver = servicesFactory.makeTaskSaver();
            cachedTaskController = new TaskController(taskGetter, taskCreator, taskSaver);
        }
        return cachedTaskController;
    }

    public EventController makeEventController(CalendarEventPresenter eventPresenter) {
        if (cachedEventController == null ) {
            CalendarEventCreationBoundary eventCreator = servicesFactory.makeEventCreator();
            EventGetter eventGetter = servicesFactory.makeEventOutputter(eventPresenter);
            EventSaver eventSaver = servicesFactory.makeEventSaver();
            cachedEventController = new EventController(eventCreator, eventGetter, eventSaver);
        }
        return cachedEventController;
    }

    public EventController getCachedEventController() {
        return cachedEventController;
    }

    public TaskToEventController makeTaskToEventController() {
        if (cachedTaskToEventController == null) {
            CalendarAnalyzer calendarAnalyzer = servicesFactory.makeCalendarAnalyzer();
            cachedTaskToEventController = new TaskToEventController(getCachedEventController(), calendarAnalyzer);
        }
        return cachedTaskToEventController;

    }

    public PomodoroController makePomodoroController() {
        if (cachedPomodoroController == null)
            cachedPomodoroController = new PomodoroController();
        return cachedPomodoroController;
    }

}
