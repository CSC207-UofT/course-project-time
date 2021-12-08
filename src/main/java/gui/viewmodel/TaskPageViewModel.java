package gui.viewmodel;

import datagateway.task.ObservableTaskRepository;
import datagateway.task.TaskReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TaskPageViewModel extends ViewModel {

    private final ObservableTaskRepository repository;

    private final ObservableMap<String, String> taskInfoMap;

    public TaskPageViewModel(ObservableTaskRepository repository) {
        this.repository = repository;

        // initialize taskInfoMap
        taskInfoMap = FXCollections.observableHashMap();
    }

    public Map<String, String> getTaskInfo(long taskId) {
        this.taskInfoMap.clear();

        TaskReader tr = repository.getTask(taskId);
        taskInfoMap.put("name", tr.getName());

        String dueDate = "";
        String dueTimeHours = "";
        String dueTimeMinutes = "";
        if (tr.getDeadline() != null) {
            dueDate = tr.getDeadline().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
            );
            dueTimeHours = tr.getDeadline().format(
                    DateTimeFormatter.ofPattern("HH")
            );
            dueTimeMinutes = tr.getDeadline().format(
                    DateTimeFormatter.ofPattern("mm")
            );
        }
        taskInfoMap.put("dueDate", dueDate);
        taskInfoMap.put("dueTimeHours", dueTimeHours);
        taskInfoMap.put("dueTimeMinutes", dueTimeMinutes);

        String completed = tr.getCompleted() ? "yes" : "no";
        taskInfoMap.put("completed", completed);

        String subTasks = tr.getSubtasks().toString();
        return null;  // todo
    }

}
