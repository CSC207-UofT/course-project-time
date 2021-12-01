package services.services_factory;

import data_gateway.CalendarManager;
import data_gateway.TodoListManager;

/**
 * Factory Method for creating entity repositories
 */
public interface RepositoryFactory {

    CalendarManager makeEventRepository();
    TodoListManager makeTaskRepository();

}
