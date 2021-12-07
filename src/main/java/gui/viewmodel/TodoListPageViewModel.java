package gui.viewmodel;

import datagateway.Observer;
import datagateway.task.ObservableTaskRepository;
import datagateway.task.TaskReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class TodoListPageViewModel extends ViewModel {

    private final ObservableTaskRepository repository;

    private final ObservableMap<String, String> taskInfoMap;

    public TodoListPageViewModel(ObservableTaskRepository repository) {
        this.repository = repository;
        repository.addCreationObserver(this::handleCreation);
        repository.addUpdateObserver(this::handleUpdate);

        List<TaskReader> taskList = repository.getAllTasks().get(0L);
        this.taskInfoMap = generateTaskNameDeadlineMap(taskList);
    }

    private ObservableMap<String, String> generateTaskNameDeadlineMap(List<TaskReader> taskList) {
        ObservableMap<String, String> nameDeadlineMap = FXCollections.observableHashMap();
        for (TaskReader taskReader : taskList) {
            String taskName = taskReader.getName();

            String deadline = new String("No Deadline");
            if (taskReader.getDeadline() != null) {
                deadline = taskReader.getDeadline().format(
                        DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM, // The format for date
                                FormatStyle.SHORT)); // The format for time
            }

            nameDeadlineMap.put(taskName, deadline);
        }
        return nameDeadlineMap;
    }

    public ObservableMap<String, String> getTaskInfoMap() {
        return this.taskInfoMap;
    }

    public void handleCreation(TaskReader taskReader) {

    }

    public void handleUpdate(TaskReader taskReader) {

    }

}
