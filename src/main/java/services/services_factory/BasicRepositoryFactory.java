package services.services_factory;

import data_gateway.event.CalendarManager;
import data_gateway.event.EventEntityManager;
import data_gateway.task.TodoEntityManager;
import data_gateway.task.TodoListManager;
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
        return null;
    }

    @Override
    public TodoListManager makeTaskRepository() {
        if (cachedTaskRepository == null)
            cachedTaskRepository = new TodoEntityManager(snowflake);
        return cachedTaskRepository;
    }
}
