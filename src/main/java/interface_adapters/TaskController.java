package main.java.interface_adapters;

import main.java.entity.Task;
import main.java.use_case.AccessTodoData;
import main.java.use_case.NewTodoListTaskData;
import main.java.use_case.TaskAdder;
import main.java.use_case.TaskGetter;
import java.time.LocalDateTime;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskController {
    private final AccessTodoData todoData;
    private final TaskGetter taskGetter;
    private final TaskAdder taskAdder;

    public TaskController(AccessTodoData todoData, TaskGetter taskGetter, TaskAdder taskAdder) {
        this.todoData = todoData;
        this.taskGetter = taskGetter;
        this.taskAdder = taskAdder;
    }

    /**
     * @return a list of tasks organized in map format, with
     * "name", "deadline", "subtasks", and "completed" as keys
     */
    public List<HashMap<String, String>> getTasks() {
        return taskGetter.getTasks(todoData);
    }

    public boolean createTask(String taskName, Duration timeNeeded) {
        return this.createTask(taskName, timeNeeded, null, new ArrayList<>());
    }

    public boolean createTask(String taskName, Duration timeNeeded,
                              LocalDateTime deadline, List<String> subTasks) {
        taskAdder.addTask(new NewTodoListTaskData(0, taskName, timeNeeded, deadline, subTasks));
        return true; // TODO: return value should indicate success of data creation
    }

    /**
     * Gets a Task by its name
     * @param name name of Task
     * @return Task with given name
     */
    public Task getTaskByName(String name) {
        return taskGetter.getTaskByName(name, todoData);
    }
}
