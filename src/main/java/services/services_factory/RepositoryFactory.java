package services.services_factory;


import data_gateway.event.CalendarManager;
import data_gateway.task.TodoListManager;

/**
 * Factory Method for creating entity repositories
 */
public interface RepositoryFactory {

    CalendarManager makeEventRepository();
    TodoListManager makeTaskRepository();

}
