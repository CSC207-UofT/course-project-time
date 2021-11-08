package main.java.use_case;

import main.java.entity.Task;
import main.java.entity_gateway.TaskReader;
import main.java.entity_gateway.TodoListManager;

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

    public TodoListOutputDTO getTasks() {
        Map<Integer, List<TaskReader>> taskReaders = todoListManager.getAllTasks();
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

    public TaskOutputDTO getTaskByName(String name) {
        Map<Integer, List<TaskReader>> taskMap = todoListManager.getAllTasks();
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
        Map<Integer, List<TaskReader>> taskReaders = todoListManager.getAllTasks();
        TodoListOutputDTO todoListInfo = new TodoListInfoFromTaskReaders(taskReaders);
        taskPresenter.presentTasks(todoListInfo);
    }
}
