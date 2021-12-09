package services.taskpresentation;

import datagateway.task.TaskReader;
import datagateway.task.TodoListManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskOutputter implements TodoListDisplayBoundary {
    private final TodoListManager todoListManager;
    private final TodoListPresenter taskPresenter;

    public TaskOutputter(TodoListManager todoListManager, TodoListPresenter taskPresenter) {
        this.todoListManager = todoListManager;
        this.taskPresenter = taskPresenter;
    }
    /**
     * Get tasks from the database through data gateway and
     * pass the tasks as DTOs to the presenter to present all tasks.
     */
    @Override
    public void presentAllTasks() {
        List<TaskReader> taskReaders = todoListManager.getAllTasks();
        List<TaskInfo> taskInfos = new ArrayList<>();
        taskReaders.forEach(tr -> taskInfos.add(new TaskInfoFromTaskReader(tr)));
        taskPresenter.presentTasks(taskInfos);
    }


    /**
     * Get tasks from the database through data gateway and
     * pass the tasks as DTOs to the presenter to present all tasks in an ordered list.
     * @return a mapping of task's position in the presented list and id
     */
    @Override
    public Map<Integer, Long> presentAllTasksForUserSelection() {
        List<TaskReader> taskReaders = todoListManager.getAllTasks();
        List<TaskInfo> taskInfos = new ArrayList<>();
        taskReaders.forEach(tr -> taskInfos.add(new TaskInfoFromTaskReader(tr)));
        return taskPresenter.presentTasksForUserSelection(taskInfos);
    }

}
