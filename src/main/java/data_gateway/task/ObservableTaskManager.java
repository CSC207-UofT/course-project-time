package data_gateway.task;

import data_gateway.PropertyObserver;

public interface ObservableTaskManager extends TodoListManager {

    void addOnCreationObserver(PropertyObserver<TaskReader, Long> observer);
    void addOnCompletionUpdateObserver(PropertyObserver<TaskReader, Boolean> observer);
}
