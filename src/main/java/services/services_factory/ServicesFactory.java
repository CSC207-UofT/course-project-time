package services.services_factory;

import services.event_creation.CalendarEventCreationBoundary;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_presentation.CalendarEventDisplayBoundary;
import services.event_presentation.CalendarEventPresenter;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListPresenter;

/**
 * Abstract Factory for creating service-layer use cases
 */
public interface ServicesFactory {
    CalendarAnalyzer makeCalendarAnalyzer();
    CalendarEventCreationBoundary makeEventCreator();
    CalendarEventDisplayBoundary makeEventOutputter(CalendarEventPresenter eventPresenter);
    TodoListTaskCreationBoundary makeTaskCreator();
    TodoListDisplayBoundary makeTaskOutputter(TodoListPresenter taskPresenter);
}
