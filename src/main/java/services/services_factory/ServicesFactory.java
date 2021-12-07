package services.services_factory;

import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_presentation.CalendarEventDisplayBoundary;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.CalendarEventRequestBoundary;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListPresenter;
import services.task_presentation.TodoListRequestBoundary;
import services.update_entities.UpdateEventBoundary;
import services.update_entities.UpdateTaskBoundary;

/**
 * Abstract Factory for creating service-layer use cases
 */
public interface ServicesFactory {
    CalendarAnalyzer makeCalendarAnalyzer();
    CalendarEventCreationBoundary makeEventCreator();
    CalendarEventDisplayBoundary makeEventOutputter(CalendarEventPresenter eventPresenter);
    CalendarEventRequestBoundary makeEventGetter();
    EventSaver makeEventSaver();
    UpdateEventBoundary makeEventUpdater();
    TodoListTaskCreationBoundary makeTaskCreator();
    TodoListDisplayBoundary makeTaskOutputter(TodoListPresenter taskPresenter);
    TodoListRequestBoundary makeTaskGetter();
    TaskSaver makeTaskSaver();
    UpdateTaskBoundary makeTaskUpdater();
}
