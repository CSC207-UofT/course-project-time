package services.servicesfactory;

import datagateway.ICSExporter;
import datagateway.ICSGateway;
import datagateway.event.CalendarManager;
import datagateway.event.EventEntityManager;
import datagateway.task.TodoEntityManager;
import datagateway.task.TodoListManager;
import services.Snowflake;
import services.eventcreation.ICSSaver;

/**
 * Create general entity repositories
 */
public class BasicRepositoryFactory implements RepositoryFactory {

    private CalendarManager cachedEventRepository;
    private TodoListManager cachedTaskRepository;
    private ICSGateway cachedICSSaver;

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
    public ICSGateway makeICSGateway() {
        if (cachedICSSaver == null)
            cachedICSSaver = new ICSExporter();
        return cachedICSSaver;
    }


}
