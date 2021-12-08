package services.servicesfactory;

import datagateway.event.CalendarManager;
import database.EventEntityManager;
import datagateway.task.TodoEntityManager;
import datagateway.task.TodoListManager;
import services.Snowflake;

/**
 * Create general entity repositories
 */
public class BasicRepositoryFactory implements RepositoryFactory {

    private CalendarManager cachedEventRepository;
    private TodoListManager cachedTaskRepository;

    private final Snowflake snowflake = new Snowflake(0, 0, 0);

    @Override
    public CalendarManager makeEventRepository() {
        if (cachedEventRepository == null)
            cachedEventRepository = new EventEntityManager(snowflake);
        return cachedEventRepository;
    }

    @Override
    public TodoListManager makeTaskRepository() {
        if (cachedTaskRepository == null)
            cachedTaskRepository = new TodoEntityManager(snowflake);
        return cachedTaskRepository;
    }
}
