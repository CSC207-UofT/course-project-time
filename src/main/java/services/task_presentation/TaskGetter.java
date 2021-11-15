package services.task_presentation;

import data_gateway.TaskReader;
import data_gateway.TodoListManager;

import java.util.List;
import java.util.Map;

public class TaskGetter implements TodoListDisplayBoundary {

    private final TodoListManager todoListManager;
    private final TodoListPresenter taskPresenter;

    public TaskGetter(TodoListManager todoListManager, TodoListPresenter taskPresenter) {
        this.todoListManager = todoListManager;
        this.taskPresenter = taskPresenter;
    }

    /**
     * Get task by id.
     * @param id id of a task
     * @return the corresponding task as a TaskInfo
     */
    public TaskInfo getTaskById(Long id) {
        Map<Long, List<TaskReader>> taskMap = todoListManager.getAllTasks();
        for (List<TaskReader> todoListTasks : taskMap.values())
            for (TaskReader tr : todoListTasks) {
                if (((Long) tr.getId()).equals(id)) {
                    return new TaskInfoFromTaskReader(tr);
                }
            }
        return null;
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
