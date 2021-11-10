package main.java.interface_adapters;

import main.java.use_case.*;

import java.io.IOException;
import java.time.LocalDateTime;

import java.time.Duration;
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
