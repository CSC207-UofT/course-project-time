package services.services_factory;

import data_gateway.event.CalendarManager;
import data_gateway.task.TodoListManager;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventAdder;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_from_task_creation.EventScheduler;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.EventGetter;
import services.task_creation.TaskAdder;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskGetter;
import services.task_presentation.TodoListPresenter;

public class BasicServiceFactory implements ServicesFactory {

    private final CalendarManager eventRepository;
    private final TodoListManager taskRepository;

    private CalendarAnalyzer cachedAnalyzer;
    private CalendarEventCreationBoundary cachedEventCreator;
    private EventGetter cachedEventOutputter;
    private EventSaver cachedEventSaver;
    private TodoListTaskCreationBoundary cachedTaskCreator;
    private TaskGetter cachedTaskOutputter;
    private TaskSaver cachedTaskSaver;


    public BasicServiceFactory(RepositoryFactory repositoryFactory) {
        this.eventRepository = repositoryFactory.makeEventRepository();
        this.taskRepository = repositoryFactory.makeTaskRepository();
    }

    @Override
    public CalendarAnalyzer makeCalendarAnalyzer() {
        if (cachedAnalyzer == null)
            cachedAnalyzer = new EventScheduler(eventRepository);
        return cachedAnalyzer;
    }

    @Override
    public CalendarEventCreationBoundary makeEventCreator() {
        if (cachedEventCreator == null)
            cachedEventCreator = new EventAdder(eventRepository);
        return cachedEventCreator;
    }

    @Override
    public EventGetter makeEventOutputter(CalendarEventPresenter eventPresenter) {
        if (cachedEventOutputter == null)
            cachedEventOutputter = new EventGetter(eventRepository, eventPresenter);
        return cachedEventOutputter;
    }

    @Override
    public EventSaver makeEventSaver() {
        if (cachedEventSaver == null)
            cachedEventSaver = new EventSaver(eventRepository);
        return cachedEventSaver;
    }

    @Override
    public TodoListTaskCreationBoundary makeTaskCreator() {
        if (cachedTaskCreator == null)
            cachedTaskCreator = new TaskAdder(taskRepository);
        return cachedTaskCreator;
    }

    @Override
    public TaskGetter makeTaskOutputter(TodoListPresenter taskPresenter) {
        if (cachedTaskOutputter == null)
            cachedTaskOutputter = new TaskGetter(taskRepository, taskPresenter);
        return cachedTaskOutputter;
    }

    @Override
    public TaskSaver makeTaskSaver() {
        if (cachedTaskSaver == null)
            cachedTaskSaver = new TaskSaver(taskRepository);
        return cachedTaskSaver;
    }
}
