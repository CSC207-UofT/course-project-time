package services.servicesfactory;

import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;
import datagateway.task.TodoListManager;

public interface ObservableRepositoryFactory extends RepositoryFactory {

    @Override
    ObservableEventRepository makeEventRepository();

    @Override
    ObservableTaskRepository makeTaskRepository();
}
