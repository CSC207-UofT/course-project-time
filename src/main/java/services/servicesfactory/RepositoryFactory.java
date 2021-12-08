package services.servicesfactory;


import datagateway.ICSGateway;
import datagateway.event.CalendarManager;
import datagateway.task.TodoListManager;
import services.eventcreation.ICSSaver;

/**
 * Factory Method for creating entity repositories
 */
public interface RepositoryFactory {

    CalendarManager makeEventRepository();
    TodoListManager makeTaskRepository();

    ICSGateway makeICSGateway();
}
