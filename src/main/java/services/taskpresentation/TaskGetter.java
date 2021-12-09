package services.taskpresentation;

import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskGetter implements TodoListRequestBoundary {

    private final TodoListManager todoListManager;

    public TaskGetter(TodoListManager todoListManager) {
        this.todoListManager = todoListManager;
    }

    /**
     * Get task by id.
     * @param id id of a task
     * @return the corresponding task as a TaskInfo
     */
    @Override
    public TaskInfo getTaskById(Long id) {
        List<TaskReader> taskList = todoListManager.getAllTasks();
        for (TaskReader tr : taskList) {
            if (((Long) tr.getId()).equals(id)) {
                return new TaskInfoFromTaskReader(tr);
            }
        }
        return null;
    }

    /**
     * Get all tasks.
     * @return a list of TaskInfo
     */
    @Override
    public List<TaskInfo> getTasks() {
        List<TaskReader> taskList = todoListManager.getAllTasks();
        List<TaskInfo> taskInfos = new ArrayList<>();
        for (TaskReader tr : taskList) {
            taskInfos.add(new TaskInfoFromTaskReader(tr));
        }
        return taskInfos;
    }

}
