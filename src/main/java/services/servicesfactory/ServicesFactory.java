package services.servicesfactory;

import services.eventcreation.CalendarEventCreationBoundary;
import services.eventcreation.EventSaver;
import services.eventcreation.ICSSaver;
import services.eventdeletion.EventDeletionBoundary;
import services.eventfromtaskcreation.CalendarAnalyzer;
import services.eventpresentation.CalendarEventDisplayBoundary;
import services.eventpresentation.CalendarEventPresenter;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.taskcreation.TaskSaver;
import services.taskcreation.TodoListTaskCreationBoundary;
import services.taskdeletion.TaskDeletionBoundary;
import services.taskpresentation.TodoListDisplayBoundary;
import services.taskpresentation.TodoListPresenter;
import services.taskpresentation.TodoListRequestBoundary;
import services.updateentities.UpdateEventBoundary;
import services.updateentities.UpdateTaskBoundary;

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
    EventDeletionBoundary makeEventDeleter();
    TodoListTaskCreationBoundary makeTaskCreator();
    TodoListDisplayBoundary makeTaskOutputter(TodoListPresenter taskPresenter);
    TodoListRequestBoundary makeTaskGetter();
    TaskSaver makeTaskSaver();
    UpdateTaskBoundary makeTaskUpdater();
    TaskDeletionBoundary makeTaskDeleter();
    ICSSaver makeICSSaver();
}
