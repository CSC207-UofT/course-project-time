package gui.viewmodel;

import datagateway.task.TaskReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.taskpresentation.TaskInfo;
import services.taskpresentation.TaskInfoFromTaskReader;
import services.taskpresentation.TodoListRequestBoundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TodoListPageViewModel extends ViewModel {

    private final ObservableList<TaskInfo> taskInfoList;
    private final ObservableList<Map<String, String>> viewInfoList;
    private final TaskDataBinding taskDataBinding;

    public TodoListPageViewModel(TodoListRequestBoundary taskGetter, TaskDataBinding taskDataBinding) {

        this.taskDataBinding = taskDataBinding;

        // initialize sorted taskInfoList
        List<TaskInfo> taskList = taskGetter.getTasks();
        this.taskInfoList = FXCollections.observableArrayList();
        for (TaskInfo taskInfo : taskList) {
            this.insertTaskInfo(taskInfo);
        }
        // initialize viewInfoList accordingly
        this.viewInfoList = FXCollections.observableArrayList();
        this.updateViewInfoList();
    }

    /***
     * helper function to sort the taskInfoList based on the deadline
     * @param taskInfo to inserted to taskInfoList
     */
    private void insertTaskInfo(TaskInfo taskInfo) {
        // put tasks with no deadlines to the end of the list
        if (taskInfo.getDeadline() != null) {
            LocalDateTime deadline = taskInfo.getDeadline();
            int i = 0;
            while (i < this.taskInfoList.size()) {
                if (taskInfoList.get(i).getDeadline() != null) {
                    if (deadline.isEqual(taskInfoList.get(i).getDeadline()) ||
                            (deadline.isBefore(taskInfoList.get(i).getDeadline()))) {
                        taskInfoList.add(i, taskInfo);
                        return;
                    }
                } else {
                    taskInfoList.add(i, taskInfo);
                    return;
                }
                i = i + 1;
            }
        }
        taskInfoList.add(taskInfo);
    }

    /***
     * Update viewInfoList to make it consistent with taskReaderList
     */
    public void updateViewInfoList() {
        this.viewInfoList.clear();
        for (TaskInfo taskInfo : taskInfoList) {
            String id = String.valueOf(taskInfo.getId());
            String taskName = taskInfo.getName();

            String deadline = new String("No Deadline");
            if (taskInfo.getDeadline() != null) {
                deadline = taskInfo.getDeadline().format(
                        DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM, // The format for date
                                FormatStyle.SHORT)); // The format for time
            }

            Map<String, String> taskViewInfo = new HashMap<String, String>();
            taskViewInfo.put("id", id);
            taskViewInfo.put("taskName", taskName);
            taskViewInfo.put("deadline", deadline);
            this.viewInfoList.add(taskViewInfo);
        }
    }

    public void taskSelected(long taskId) {
        taskDataBinding.setTaskId(taskId);
    }

    public ObservableList<Map<String, String>> getTaskInfoList() {
        return viewInfoList;
    }

    public void handleCreation(TaskReader taskReader) {
        String id = String.valueOf(taskReader.getId());
        String taskName = taskReader.getName();
        String deadline;
        if (taskReader.getDeadline() != null) {
            deadline = taskReader.getDeadline().format(
                    DateTimeFormatter.ofLocalizedDateTime(
                            FormatStyle.MEDIUM, // The format for date
                            FormatStyle.SHORT)
            );
        } else {
            deadline = "No Deadline";
        }

        Map<String, String> taskInfoMap = new HashMap<>();
        taskInfoMap.put("id", id);
        taskInfoMap.put("taskName", taskName);
        taskInfoMap.put("deadline", deadline);

        this.viewInfoList.add(taskInfoMap);
    }

    public void handleUpdate(TaskReader taskReader) {
        long taskId = taskReader.getId();

        // delete the outdated task before putting in the new one
        taskInfoList.removeIf(taskInfo -> taskInfo.getId() == taskId);

        // put the updated task into the taskInfoList
        TaskInfo updatedTask = new TaskInfoFromTaskReader(taskReader);
        insertTaskInfo(updatedTask);

        // update the view
        updateViewInfoList();
    }
}
