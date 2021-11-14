package main.java.console_app.task_adapters;

import main.java.services.task_creation.TaskSaver;
import main.java.services.task_creation.TodoListTaskCreationBoundary;
import main.java.services.task_presentation.TaskGetter;
import main.java.services.task_presentation.TaskInfo;
import main.java.services.task_presentation.TodoListsInfo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private final TaskGetter taskGetter;
    private final TodoListTaskCreationBoundary taskAdder;
    private final TaskSaver taskSaver;

    public TaskController(TaskGetter taskGetter, TodoListTaskCreationBoundary taskAdder, TaskSaver taskSaver) {
        this.taskGetter = taskGetter;
        this.taskAdder = taskAdder;
        this.taskSaver = taskSaver;
    }

    /**
     * @return a list of tasks organized in map format, with
     * "name", "deadline", "subtasks", and "completed" as keys
     */
    public TodoListsInfo getTasks() {
        return taskGetter.getTasks();
    }

    public boolean createTask(String taskName, Duration timeNeeded) {
        return this.createTask(taskName, timeNeeded, null, new ArrayList<>());
    }

    public boolean createTask(String taskName, Duration timeNeeded,
                              LocalDateTime deadline, List<String> subTasks) {
        taskAdder.addTask(new NewTodoListTaskData(0, taskName, timeNeeded, deadline, subTasks));
        return true; // TODO: return value should indicate success of data creation
    }

    public boolean completeTask(long taskId) {
        taskAdder.completeTask(taskId);
        return true;
    }

    /**
     * Gets a Task by its name
     * @param name name of Task
     * @return TaskInfo with given name
     */
    public TaskInfo getTaskByName(String name) {
        return taskGetter.getTaskByName(name);
    }

    public void saveTodoList(String filename) throws IOException {
        taskSaver.save(filename);
    }
}
