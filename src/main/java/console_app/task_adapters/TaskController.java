package main.java.console_app.task_adapters;

import main.java.services.task_creation.TodoListTaskCreationBoundary;
import main.java.services.task_presentation.TaskGetter;
import main.java.services.task_presentation.TaskInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private final TaskGetter taskGetter;
    private final TodoListTaskCreationBoundary taskAdder;

    public TaskController(TaskGetter taskGetter, TodoListTaskCreationBoundary taskAdder) {
        this.taskGetter = taskGetter;
        this.taskAdder = taskAdder;
    }

    /**
     * Displays all tasks.
     */
    public void presentAllTasks() {
        taskGetter.presentAllTasks();
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
     * @return TaskInfo with given name
     */
    public TaskInfo getTaskByName(String name) {
        return taskGetter.getTaskByName(name);
    }
}
