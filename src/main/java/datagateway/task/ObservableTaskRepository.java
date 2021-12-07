package datagateway.task;

import datagateway.ObservableRepository;

/**
 * Interface allowing implementors polymorphic access to
 * {@link TodoListManager} and {@link ObservableRepository<TaskReader>}
 */
public interface ObservableTaskRepository extends ObservableRepository<TaskReader>, TodoListManager {
}
