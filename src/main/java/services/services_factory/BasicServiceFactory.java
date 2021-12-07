package services.services_factory;

import data_gateway.event.CalendarManager;
import data_gateway.task.TodoListManager;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventAdder;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_from_task_creation.EventScheduler;
import services.event_presentation.CalendarEventDisplayBoundary;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.CalendarEventRequestBoundary;
import services.event_presentation.EventGetter;
import services.event_presentation.EventOutputter;
import services.task_creation.TaskAdder;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskGetter;
import services.task_presentation.TaskOutputter;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListPresenter;
import services.task_presentation.TodoListRequestBoundary;
import services.update_entities.EventUpdater;
import services.update_entities.TaskUpdater;
import services.update_entities.UpdateEventBoundary;
import services.update_entities.UpdateTaskBoundary;

public class BasicServiceFactory implements ServicesFactory {

    private final CalendarManager eventRepository;
    private final TodoListManager taskRepository;

    private CalendarAnalyzer cachedAnalyzer;
    private CalendarEventCreationBoundary cachedEventCreator;
    private CalendarEventDisplayBoundary cachedEventOutputter;
    private CalendarEventRequestBoundary cachedEventGetter;
    private EventSaver cachedEventSaver;
    private UpdateEventBoundary cachedEventUpdater;
    private TodoListTaskCreationBoundary cachedTaskCreator;
    private TodoListDisplayBoundary cachedTaskOutputter;
    private TodoListRequestBoundary cachedTaskGetter;
    private TaskSaver cachedTaskSaver;
    private UpdateTaskBoundary cachedTaskUpdater;


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
    public CalendarEventDisplayBoundary makeEventOutputter(CalendarEventPresenter eventPresenter) {
        if (cachedEventOutputter == null)
            cachedEventOutputter = new EventOutputter(eventRepository, eventPresenter);
        return cachedEventOutputter;
    }

    @Override
    public CalendarEventRequestBoundary makeEventGetter() {
        if (cachedEventGetter == null)
            cachedEventGetter = new EventGetter(eventRepository);
        return cachedEventGetter;
    }

    @Override
    public EventSaver makeEventSaver() {
        if (cachedEventSaver == null)
            cachedEventSaver = new EventSaver(eventRepository);
        return cachedEventSaver;
    }

    @Override
    public UpdateEventBoundary makeEventUpdater() {
        if (cachedEventUpdater == null)
            cachedEventUpdater = new EventUpdater(eventRepository);
        return cachedEventUpdater;
    }

    @Override
    public TodoListTaskCreationBoundary makeTaskCreator() {
        if (cachedTaskCreator == null)
            cachedTaskCreator = new TaskAdder(taskRepository);
        return cachedTaskCreator;
    }

    @Override
    public TodoListDisplayBoundary makeTaskOutputter(TodoListPresenter taskPresenter) {
        if (cachedTaskOutputter == null)
            cachedTaskOutputter = new TaskOutputter(taskRepository, taskPresenter);
        return cachedTaskOutputter;
    }

    @Override
    public TodoListRequestBoundary makeTaskGetter() {
        if (cachedTaskGetter == null)
            cachedTaskGetter = new TaskGetter(taskRepository);
        return cachedTaskGetter;
    }

    @Override
    public TaskSaver makeTaskSaver() {
        if (cachedTaskSaver == null)
            cachedTaskSaver = new TaskSaver(taskRepository);
        return cachedTaskSaver;
    }

    @Override
    public UpdateTaskBoundary makeTaskUpdater() {
        if (cachedTaskUpdater == null)
            cachedTaskUpdater = new TaskUpdater(taskRepository);
        return cachedTaskUpdater;
    }
}
