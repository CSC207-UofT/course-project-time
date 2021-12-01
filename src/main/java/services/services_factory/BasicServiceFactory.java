package services.services_factory;

import data_gateway.CalendarManager;
import data_gateway.TodoListManager;
import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventAdder;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_from_task_creation.EventScheduler;
import services.event_presentation.CalendarEventDisplayBoundary;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.EventGetter;
import services.task_creation.TaskAdder;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskGetter;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListPresenter;

public class BasicServiceFactory implements ServicesFactory {

    private final CalendarManager eventRepository;
    private final TodoListManager taskRepository;

    private CalendarAnalyzer cachedAnalyzer;
    private CalendarEventCreationBoundary cachedEventCreator;
    private CalendarEventDisplayBoundary cachedEventOutputter;
    private TodoListTaskCreationBoundary cachedTaskCreator;
    private TodoListDisplayBoundary cachedTaskOutputter;


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
            cachedEventOutputter = new EventGetter(eventRepository, eventPresenter);
        return cachedEventOutputter;
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
            cachedTaskOutputter = new TaskGetter(taskRepository, taskPresenter);
        return cachedTaskOutputter;
    }
}
