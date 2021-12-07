package gui.viewmodel;

import datagateway.Observer;
import datagateway.task.ObservableTaskRepository;
import datagateway.task.TaskReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
 * taskReaderList: a sorted list based on the deadline of tasks.
 *                 tasks without deadline will be put to the end of the list
 * taskInfoList: a list of maps of task info corresponding to taskReaderList
 */
public class TodoListPageViewModel extends ViewModel implements Observer<TaskReader> {

    private final ObservableTaskRepository repository;

    private final ObservableList<TaskReader> taskReaderList;
    private final ObservableList<Map<String, String>> taskInfoList;

    public TodoListPageViewModel(ObservableTaskRepository repository) {
        this.repository = repository;
        repository.addCreationObserver(this::handleCreation);
        repository.addUpdateObserver(this::handleUpdate);

        // initialize sorted taskReaderList
        List<TaskReader> taskList = repository.getAllTasks().get(0L);
        this.taskReaderList = FXCollections.observableArrayList();
        for (TaskReader taskReader : taskList) {
            this.insertTaskReader(taskReader);
        }
        // initialize taskInfoList accordingly
        this.taskInfoList = FXCollections.observableArrayList();
        this.updateTaskInfoList();
    }

    /***
     * helper function to sort the taskReaderList based on the deadline
     * @param taskReader to inserted to taskReadList
     */
    private void insertTaskReader(TaskReader taskReader) {
        // put the non-deadline task to the end of the list
        if (taskReader.getDeadline() != null) {
            LocalDateTime deadline = taskReader.getDeadline();
            int i = 0;
            while (i < this.taskReaderList.size()) {
                if (taskReaderList.get(i).getDeadline() != null) {
                    if (deadline.isEqual(taskReaderList.get(i).getDeadline()) ||
                            (deadline.isBefore(taskReaderList.get(i).getDeadline()))) {
                        taskReaderList.add(i, taskReader);
                        return;
                    }
                } else {
                    taskReaderList.add(i, taskReader);
                    return;
                }
                i = i + 1;
            }
        }
        taskReaderList.add(taskReader);
    }

    /***
     * Update TaskInfoList to make it consistent with taskReaderList
     */
    public void updateTaskInfoList() {
        this.taskInfoList.clear();
        for (TaskReader taskReader : this.taskReaderList) {
            String id = String.valueOf(taskReader.getId());
            String taskName = taskReader.getName();

            String deadline = new String("No Deadline");
            if (taskReader.getDeadline() != null) {
                deadline = taskReader.getDeadline().format(
                        DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM, // The format for date
                                FormatStyle.SHORT)); // The format for time
            }

            Map<String, String> taskInfo = new HashMap<String, String>();
            taskInfo.put("id", id);
            taskInfo.put("taskName", taskName);
            taskInfo.put("deadline", deadline);
            this.taskInfoList.add(taskInfo);
        }
    }

    public ObservableList<Map<String, String>> getTaskInfoList() {
        return taskInfoList;
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

        this.taskInfoList.add(taskInfoMap);
    }

    public void handleUpdate(TaskReader taskReader) {

    }

    @Override
    public void notifyObserver(TaskReader entity) {

    }
}
