package services.servicesfactory;

import datagateway.event.CalendarManager;
import datagateway.event.EventEntityManager;
import datagateway.notification.NotificationEntityManager;
import datagateway.notification.NotificationManager;
import datagateway.task.TodoEntityManager;
import datagateway.task.TodoListManager;
import services.Snowflake;

/**
 * Create general entity repositories
 */
public class BasicRepositoryFactory implements RepositoryFactory {

    private CalendarManager cachedEventRepository;
    private TodoListManager cachedTaskRepository;
    private NotificationManager cachedNotificationRepository;

    private final Snowflake snowflake = new Snowflake(0, 0, 0);

    @Override
    public CalendarManager makeEventRepository() {
        if (cachedEventRepository == null)
            cachedEventRepository = new EventEntityManager(snowflake, makeTaskRepository());
        return cachedEventRepository;
    }

    @Override
    public TodoListManager makeTaskRepository() {
        if (cachedTaskRepository == null)
            cachedTaskRepository = new TodoEntityManager(snowflake);
        return cachedTaskRepository;
    }

    @Override
    public NotificationManager makeNotificationRepository() {
        if (cachedNotificationRepository == null)
            cachedNotificationRepository = new NotificationEntityManager(snowflake);
        return cachedNotificationRepository;
    }
}
