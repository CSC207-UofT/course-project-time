package services.services_factory;

import data_gateway.CalendarManager;
import data_gateway.EventEntityManager;
import data_gateway.TodoEntityManager;
import data_gateway.TodoListManager;
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
