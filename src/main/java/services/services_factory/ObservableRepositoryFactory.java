package services.services_factory;

import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskRepository;

public interface ObservableRepositoryFactory extends RepositoryFactory {

    @Override
    ObservableEventRepository makeEventRepository();

    @Override
    ObservableTaskRepository makeTaskRepository();
}
