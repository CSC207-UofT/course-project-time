package main.java.services.task_presentation;

import main.java.data_gateway.TaskReader;
import main.java.data_gateway.TodoListManager;
import main.java.entity.Task;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskGetter implements TodoListDisplayBoundary {

    private final TodoListManager todoListManager;
    private final TodoListPresenter taskPresenter;

    public TaskGetter(TodoListManager todoListManager, TodoListPresenter taskPresenter) {
        this.todoListManager = todoListManager;
        this.taskPresenter = taskPresenter;
    }

    public void getTasks() {
        Map<Long, List<TaskReader>> taskReaders = todoListManager.getAllTasks();
        this.taskPresenter.presentTasks(new TodoListInfoFromTaskReaders(taskReaders));
    }

    public TaskInfo getTaskByName(String name) {
        Map<Long, List<TaskReader>> taskMap = todoListManager.getAllTasks();
        for (List<TaskReader> todoListTasks : taskMap.values())
            for (TaskReader tr : todoListTasks) {
                if (tr.getName().equals(name)) {
                    return new TaskInfoFromTaskReader(tr);
                }
            }
        return null;
    }

    @Override
    public void presentAllTodoLists() {
        Map<Long, List<TaskReader>> taskReaders = todoListManager.getAllTasks();
        TodoListsInfo todoListInfo = new TodoListInfoFromTaskReaders(taskReaders);
        taskPresenter.presentTasks(todoListInfo);
    }
}
