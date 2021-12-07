package services.servicesfactory;

import datagateway.event.ObservableEventEntityManager;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskEntityManager;
import datagateway.task.ObservableTaskRepository;

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
