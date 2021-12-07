package services.services_factory;

import data_gateway.event.ObservableEventEntityManager;
import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskEntityManager;
import data_gateway.task.ObservableTaskRepository;

public class BasicObservableRepositoryFactory implements ObservableRepositoryFactory {

    private ObservableEventRepository cachedEventRepository;
    private ObservableTaskRepository cachedTaskRepository;

    private final RepositoryFactory innerRepositoryFactory;

    public BasicObservableRepositoryFactory() {
        this(new BasicRepositoryFactory());
    }

    public BasicObservableRepositoryFactory(BasicRepositoryFactory repositoryFactory) {
        this.innerRepositoryFactory = repositoryFactory;
    }

    @Override
    public ObservableEventRepository makeEventRepository() {
        if (cachedEventRepository == null)
            cachedEventRepository = new ObservableEventEntityManager(innerRepositoryFactory.makeEventRepository());
        return cachedEventRepository;
    }

    @Override
    public ObservableTaskRepository makeTaskRepository() {
        if (cachedTaskRepository == null)
            cachedTaskRepository = new ObservableTaskEntityManager(innerRepositoryFactory.makeTaskRepository());
        return cachedTaskRepository;
    }
}
