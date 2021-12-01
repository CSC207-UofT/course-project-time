package services.services_factory;

import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventSaver;
import services.event_from_task_creation.EventScheduler;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.EventGetter;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskGetter;
import services.task_presentation.TodoListPresenter;

/**
 * Abstract Factory for creating service-layer use cases
 */
public interface ServicesFactory {
    EventScheduler makeCalendarAnalyzer();
    CalendarEventCreationBoundary makeEventCreator();
    EventGetter makeEventOutputter(CalendarEventPresenter eventPresenter);
    EventSaver makeEventSaver();
    TodoListTaskCreationBoundary makeTaskCreator();
    TaskGetter makeTaskOutputter(TodoListPresenter taskPresenter);
    TaskSaver makeTaskSaver();
}
