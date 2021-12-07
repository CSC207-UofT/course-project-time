package services.services_factory;

import services.event_creation.CalendarEventCreationBoundary;
import services.event_creation.EventAdderWithNotification;
import services.event_creation.EventSaver;
import services.event_from_task_creation.CalendarAnalyzer;
import services.event_presentation.CalendarEventDisplayBoundary;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.CalendarEventRequestBoundary;
import services.notification.NotificationAdder;
import services.task_creation.TaskAdderWithNotification;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListPresenter;
import services.task_presentation.TodoListRequestBoundary;
import services.update_entities.UpdateEventBoundary;
import services.update_entities.UpdateTaskBoundary;

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
}
