package gui.view_model;

import console_app.task_adapters.NewTodoListTaskData;
import data_gateway.task.ObservableTaskRepository;
import data_gateway.task.TaskReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AddTaskPageViewModel extends ViewModel {

    private final ObservableTaskRepository repository;

    public AddTaskPageViewModel(ObservableTaskRepository repository) {
        this.repository = repository;
        repository.addCreationObserver(this::handleCreation);
        repository.addCreationObserver(this::handleUpdate);
    }

    public void addTask(String taskName, Duration timeNeeded, LocalDateTime deadline, List<String> subTasks) {
        repository.addTask(new NewTodoListTaskData(taskName, timeNeeded, deadline, subTasks));
    }

    public void handleCreation(TaskReader taskReader) {

    }

    public void handleUpdate(TaskReader taskReader) {

    }

}
