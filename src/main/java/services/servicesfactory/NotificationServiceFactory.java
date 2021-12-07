package services.servicesfactory;

import services.eventcreation.CalendarEventCreationBoundary;
import services.eventcreation.EventAdderWithNotification;
import services.eventcreation.EventSaver;
import services.eventdeletion.EventDeletionBoundary;
import services.eventfromtaskcreation.CalendarAnalyzer;
import services.eventpresentation.CalendarEventDisplayBoundary;
import services.eventpresentation.CalendarEventPresenter;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.notification.NotificationAdder;
import services.taskcreation.TaskAdderWithNotification;
import services.taskcreation.TaskSaver;
import services.taskcreation.TodoListTaskCreationBoundary;
import services.taskdeletion.TaskDeletionBoundary;
import services.taskpresentation.TodoListDisplayBoundary;
import services.taskpresentation.TodoListPresenter;
import services.taskpresentation.TodoListRequestBoundary;
import services.updateentities.UpdateEventBoundary;
import services.updateentities.UpdateTaskBoundary;

/**
 * Factory for services with the Notification proxies
 *
 * Uses another {@link ServicesFactory} for building the non-proxied use cases
 */
public class NotificationServiceFactory implements ServicesFactory {

    private CalendarEventCreationBoundary cachedNotifEventAdder;
    private TodoListTaskCreationBoundary cachedNotifTaskAdder;

    private final NotificationAdder notificationAdder = new NotificationAdder();
    private final ServicesFactory innerFactory;

    public NotificationServiceFactory(ServicesFactory innerFactory) {
        this.innerFactory = innerFactory;
    }

    public NotificationServiceFactory(RepositoryFactory repositoryFactory) {
        this(new BasicServiceFactory(repositoryFactory));
    }

    @Override
    public CalendarAnalyzer makeCalendarAnalyzer() {
        return innerFactory.makeCalendarAnalyzer();
    }

    @Override
    public CalendarEventCreationBoundary makeEventCreator() {
        if (cachedNotifEventAdder == null)
            cachedNotifEventAdder = new EventAdderWithNotification(innerFactory.makeEventCreator(), notificationAdder);
        return cachedNotifEventAdder;
    }

    @Override
    public CalendarEventDisplayBoundary makeEventOutputter(CalendarEventPresenter eventPresenter) {
        return innerFactory.makeEventOutputter(eventPresenter);
    }

    @Override
    public CalendarEventRequestBoundary makeEventGetter() {
        return innerFactory.makeEventGetter();
    }

    @Override
    public EventSaver makeEventSaver() {
        return innerFactory.makeEventSaver();
    }

    @Override
    public UpdateEventBoundary makeEventUpdater() {
        return innerFactory.makeEventUpdater();
    }

    @Override
    public EventDeletionBoundary makeEventDeleter() {
        //TODO: develop notification-deletion family event deleter
        return innerFactory.makeEventDeleter();
    }

    @Override
    public TodoListTaskCreationBoundary makeTaskCreator() {
        if (cachedNotifTaskAdder == null)
            cachedNotifTaskAdder = new TaskAdderWithNotification(innerFactory.makeTaskCreator(), notificationAdder);
        return cachedNotifTaskAdder;
    }

    @Override
    public TodoListDisplayBoundary makeTaskOutputter(TodoListPresenter taskPresenter) {
        return innerFactory.makeTaskOutputter(taskPresenter);
    }

    @Override
    public TodoListRequestBoundary makeTaskGetter() {
        return innerFactory.makeTaskGetter();
    }

    @Override
    public TaskSaver makeTaskSaver() {
        return innerFactory.makeTaskSaver();
    }

    @Override
    public UpdateTaskBoundary makeTaskUpdater() {
        return innerFactory.makeTaskUpdater();
    }

    @Override
    public TaskDeletionBoundary makeTaskDeleter() {
        //TODO: develop notification-deletion family task deleter
        return innerFactory.makeTaskDeleter();
    }
}
