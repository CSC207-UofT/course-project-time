package services.task_presentation;

import data_gateway.task.TaskReader;
import data_gateway.task.TodoListManager;

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
        Map<Long, List<TaskReader>> taskReaders = todoListManager.getAllTasks();
        TodoListsInfo todoListInfo = new TodoListInfoFromTaskReaders(taskReaders);
        taskPresenter.presentTasks(todoListInfo);
    }


    /**
     * Get tasks from the database through data gateway and
     * pass the tasks as DTOs to the presenter to present all tasks in an ordered list.
     * @return a mapping of task's position in the presented list and id
     */
    @Override
    public Map<Integer, Long> presentAllTasksForUserSelection() {
        Map<Long, List<TaskReader>> taskReaders = todoListManager.getAllTasks();
        TodoListsInfo todoListInfo = new TodoListInfoFromTaskReaders(taskReaders);
        return taskPresenter.presentTasksForUserSelection(todoListInfo);
    }

}
