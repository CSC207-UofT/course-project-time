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

    public TodoListsInfo getTasks() {
        Map<Long, List<TaskReader>> taskReaders = todoListManager.getAllTasks();
        return new  TodoListInfoFromTaskReaders(taskReaders);
    }

    /**
     * @param task
     * @return task data organized in map format, with
     * "name", "deadline", "subtasks", and "completed" as keys
     */
    private HashMap<String, String> getTask(Task task) {
        HashMap<String, String> task_data = new HashMap<>();
        task_data.put("name", task.getTaskName());
        if(task.getDeadline() != null) {
            task_data.put("deadline", task.getDeadline().toString());
        } else {
            task_data.put("deadline", "NO DEADLINE");
        }
        task_data.put("subtasks", Arrays.toString(task.getSubTasks().toArray()));
        task_data.put("completed", Boolean.toString(task.getCompleted()));

        return task_data;
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
