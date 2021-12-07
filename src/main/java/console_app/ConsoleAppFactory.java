package console_app;


import console_app.event_adapters.EventController;
import console_app.task_adapters.ConsoleTaskPresenter;
import console_app.task_adapters.TaskController;
import console_app.task_to_event_adapters.TaskToEventController;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_presentation.CalendarEventDisplayBoundary;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.CalendarEventRequestBoundary;
import services.event_presentation.EventGetter;
import services.event_presentation.EventOutputter;
import services.services_factory.ServicesFactory;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListRequestBoundary;
import services.update_entities.UpdateEventBoundary;
import services.update_entities.UpdateTaskBoundary;

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
            TodoListDisplayBoundary taskOutputter = servicesFactory.makeTaskOutputter(taskPresenter);
            TodoListRequestBoundary taskGetter = servicesFactory.makeTaskGetter();
            TodoListTaskCreationBoundary taskCreator = servicesFactory.makeTaskCreator();
            TaskSaver taskSaver = servicesFactory.makeTaskSaver();
            UpdateTaskBoundary taskUpdater = servicesFactory.makeTaskUpdater();
            cachedTaskController = new TaskController(taskGetter, taskOutputter, taskCreator, taskSaver, taskUpdater);
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
            cachedEventController = new EventController(eventCreator, eventGetter, eventOutputter, eventSaver, eventUpdater);
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

    public PomodoroController makePomodoroController() {
        if (cachedPomodoroController == null)
            cachedPomodoroController = new PomodoroController();
        return cachedPomodoroController;
    }

}
