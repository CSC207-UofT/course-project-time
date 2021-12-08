package services.servicesfactory;

import datagateway.notification.NotificationManager;
import services.eventcreation.CalendarEventCreationBoundary;
import services.eventcreation.EventAdderWithNotification;
import services.eventcreation.EventNotificationFormatter;
import services.eventcreation.EventSaver;
import services.eventfromtaskcreation.CalendarAnalyzer;
import services.eventpresentation.CalendarEventDisplayBoundary;
import services.eventpresentation.CalendarEventPresenter;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.notification.NotificationAdder;
import services.notification.NotificationTracker;
import services.notificationsending.NotificationPresenter;
import services.taskcreation.TaskAdderWithNotification;
import services.taskcreation.TaskNotificationFormatter;
import services.taskcreation.TaskSaver;
import services.taskcreation.TodoListTaskCreationBoundary;
import services.taskpresentation.TodoListDisplayBoundary;
import services.taskpresentation.TodoListPresenter;
import services.taskpresentation.TodoListRequestBoundary;
import services.updateentities.UpdateEventBoundary;
import services.updateentities.UpdateTaskBoundary;

import java.util.List;

/**
 * Factory for services with the Notification proxies
 *
 * Uses another {@link ServicesFactory} for building the non-proxied use cases
 */
public class NotificationServiceFactory implements ServicesFactory {

    private final NotificationManager notificationManager;

    private CalendarEventCreationBoundary cachedNotifEventAdder;
    private TodoListTaskCreationBoundary cachedNotifTaskAdder;
    private NotificationTracker cachedNotifTracker;
    private NotificationAdder cachedNotificationAdder;

    private final TaskNotificationFormatter taskNotificationFormatter;
    private final EventNotificationFormatter eventNotificationFormatter;
    private final List<NotificationPresenter> presenters;
    private final ServicesFactory innerFactory;

    public NotificationServiceFactory(RepositoryFactory repositoryFactory,
                                      TaskNotificationFormatter taskNotificationFormatter,
                                      EventNotificationFormatter eventNotificationFormatter,
                                      List<NotificationPresenter> presenters) {
        notificationManager = repositoryFactory.makeNotificationRepository();
        this.taskNotificationFormatter = taskNotificationFormatter;
        this.eventNotificationFormatter = eventNotificationFormatter;
        this.presenters = presenters;
        this.innerFactory = new BasicServiceFactory(repositoryFactory);
    }

    public NotificationTracker makeNotificationTracker() {
        if (cachedNotifTracker == null) {
            cachedNotifTracker = new NotificationTracker(presenters, notificationManager);
        }
        return cachedNotifTracker;
    }

    public NotificationAdder makeNotificationAdder() {
        if (cachedNotificationAdder == null) {
            cachedNotificationAdder = new NotificationAdder(notificationManager, makeNotificationTracker());
        }
        return cachedNotificationAdder;
    }

    @Override
    public CalendarAnalyzer makeCalendarAnalyzer() {
        return innerFactory.makeCalendarAnalyzer();
    }

    @Override
    public CalendarEventCreationBoundary makeEventCreator() {
        if (cachedNotifEventAdder == null)
            cachedNotifEventAdder = new EventAdderWithNotification(innerFactory.makeEventCreator(),
                    makeNotificationAdder(), eventNotificationFormatter);
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
        if (cachedNotifTaskAdder == null) {
            cachedNotifTaskAdder = new TaskAdderWithNotification(innerFactory.makeTaskCreator(),
                    makeNotificationAdder(), taskNotificationFormatter);
        }
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
