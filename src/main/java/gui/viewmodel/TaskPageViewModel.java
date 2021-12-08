package gui.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import services.taskpresentation.TaskInfo;
import services.taskpresentation.TodoListRequestBoundary;
import services.updateentities.UpdateTaskBoundary;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskPageViewModel extends ViewModel implements PropertyChangeListener {

    private final TodoListRequestBoundary taskGetter;
    private final UpdateTaskBoundary taskUpdater;

    private final ObservableMap<String, String> taskInfoMap;

    private long taskId;

    public TaskPageViewModel(TodoListRequestBoundary taskGetter, UpdateTaskBoundary taskUpdater) {
        this.taskGetter = taskGetter;
        this.taskUpdater = taskUpdater;

        // initialize taskInfoMap
        taskInfoMap = FXCollections.observableHashMap();
    }

    /**
     * Update taskInfoMap so that its elements reflect the currently selected task
     * where the selected task is determined from the variable taskId
     */
    public void updateTaskInfoMap() {
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

    /**
     * Updates the selected task (which has id taskId)
     * @param taskName new name of task
     * @param dueDate date of the deadline
     * @param dueTimeHours hour of the deadline
     * @param dueTimeMinutes minute of the deadline
     * @param duration duration needed for the task in minutes
     * @param oldSubtasks list of subtask before the change
     * @param newSubtasks list of subtask after the change
     * @param completed whether the task is completed
     */
    public void updateTask(String taskName, LocalDate dueDate, String dueTimeHours, String dueTimeMinutes,
                           String duration, List<String> oldSubtasks, List<String> newSubtasks, boolean completed) {
        taskUpdater.updateName(taskId, taskName);

        LocalTime dueTime = LocalTime.of(Integer.parseInt(dueTimeHours), Integer.parseInt(dueTimeMinutes));
        LocalDateTime deadline = LocalDateTime.of(dueDate, dueTime);
        taskUpdater.updateDeadline(taskId, deadline);

        Duration timeNeeded = Duration.ofMinutes(Long.parseLong(duration));
        taskUpdater.updateDuration(taskId, timeNeeded);

        for (String oldSubtask : oldSubtasks) {
            taskUpdater.removeSubtask(taskId, oldSubtask);
        }
        for (String newSubtask : newSubtasks) {
            taskUpdater.addSubtask(taskId, newSubtask);
        }

        if (completed) {
            taskUpdater.completeTask(taskId);
        }
    }

    /**
     * Update the selected task (which has id taskId) that does not have a deadline
     * @param taskName new name of task
     * @param duration duration needed for the task in minutes
     * @param oldSubtasks list of subtasks before the change
     * @param newSubtasks list of subtasks after the change
     * @param completed whether the task is completed
     */
    public void updateTask(String taskName, String duration, List<String> oldSubtasks, List<String> newSubtasks,
                           boolean completed) {
        taskUpdater.updateName(taskId, taskName);

        Duration timeNeeded = Duration.ofMinutes(Long.parseLong(duration));
        taskUpdater.updateDuration(taskId, timeNeeded);

        taskUpdater.updateDeadline(taskId, null);

        for (String oldSubtask : oldSubtasks) {
            taskUpdater.removeSubtask(taskId, oldSubtask);
        }
        for (String newSubtask : newSubtasks) {
            taskUpdater.addSubtask(taskId, newSubtask);
        }

        if (completed) {
            taskUpdater.completeTask(taskId);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        taskId = (long) evt.getNewValue();
        updateTaskInfoMap();
    }

    public ObservableMap<String, String> getTaskInfoMap() {
        return taskInfoMap;
    }
}
