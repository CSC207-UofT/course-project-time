package data_gateway.task;

import data_gateway.ObservableRepository;

/**
 * Interface allowing implementors polymorphic access to
 * {@link TodoListManager} and {@link ObservableRepository<TaskReader>}
 */
public interface ObservableTaskManager extends ObservableRepository<TaskReader>, TodoListManager {
}
