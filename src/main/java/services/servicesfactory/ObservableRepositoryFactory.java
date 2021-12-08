package services.servicesfactory;

import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;

public interface ObservableRepositoryFactory extends RepositoryFactory {

    @Override
    ObservableEventRepository makeEventRepository();

    @Override
    ObservableTaskRepository makeTaskRepository();
}
