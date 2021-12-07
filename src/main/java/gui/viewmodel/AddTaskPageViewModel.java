package gui.viewmodel;

import consoleapp.taskadapters.NewTodoListTaskData;
import datagateway.task.ObservableTaskRepository;
import services.taskcreation.TodoListTaskCreationModel;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AddTaskPageViewModel extends ViewModel {

    private final ObservableTaskRepository repository;

    public AddTaskPageViewModel(ObservableTaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(String taskName, LocalDate dueDate, String dueTimeHours, String dueTimeMinutes, String duration,
                        List<String> subtasks) {
        Duration timeNeeded = Duration.ofMinutes(Long.parseLong(duration));
        LocalTime dueTime = LocalTime.of(Integer.parseInt(dueTimeHours), Integer.parseInt(dueTimeMinutes));
        LocalDateTime deadline = LocalDateTime.of(dueDate, dueTime);
        TodoListTaskCreationModel newTask = new NewTodoListTaskData(taskName, timeNeeded, deadline, subtasks);
        repository.addTask(newTask);
    }

}
