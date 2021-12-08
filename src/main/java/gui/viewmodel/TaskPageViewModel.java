package gui.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import services.taskpresentation.TaskInfo;
import services.taskpresentation.TodoListRequestBoundary;
import services.updateentities.UpdateTaskBoundary;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskPageViewModel extends ViewModel implements PropertyChangeListener {

    private final TodoListRequestBoundary taskGetter;
    private final UpdateTaskBoundary taskUpdater;

    private final ObservableMap<String, String> taskInfoMap;

    public TaskPageViewModel(TodoListRequestBoundary taskGetter, UpdateTaskBoundary taskUpdater) {
        this.taskGetter = taskGetter;
        this.taskUpdater = taskUpdater;

        // initialize taskInfoMap
        taskInfoMap = FXCollections.observableHashMap();
    }

    public void updateTaskInfoMap(long taskId) {
        this.taskInfoMap.clear();

        TaskInfo taskInfo = taskGetter.getTaskById(taskId);
        taskInfoMap.put("taskName", taskInfo.getName());

        String dueDate = "";
        String dueTimeHours = "";
        String dueTimeMinutes = "";
        if (taskInfo.getDeadline() != null) {
            dueDate = taskInfo.getDeadline().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
            );
            dueTimeHours = taskInfo.getDeadline().format(
                    DateTimeFormatter.ofPattern("HH")
            );
            dueTimeMinutes = taskInfo.getDeadline().format(
                    DateTimeFormatter.ofPattern("mm")
            );
        }
        taskInfoMap.put("dueDate", dueDate);
        taskInfoMap.put("dueTimeHours", dueTimeHours);
        taskInfoMap.put("dueTimeMinutes", dueTimeMinutes);

        taskInfoMap.put("duration", Long.toString(taskInfo.getDuration().toMinutes()));

        String completed = taskInfo.getCompleted() ? "yes" : "no";
        taskInfoMap.put("completed", completed);

        List<String> subtaskList = taskInfo.getSubtasks();
        StringBuilder subtaskSb = new StringBuilder();
        for (String s : subtaskList) {
            subtaskSb.append(s).append(" ");
        }
        taskInfoMap.put("subtasks", subtaskSb.toString());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        long taskId = (long) evt.getNewValue();
        updateTaskInfoMap(taskId);
    }

    public ObservableMap<String, String> getTaskInfoMap() {
        return taskInfoMap;
    }
}
