package main.java.controller;

import main.java.use_case.AccessTodoData;
import main.java.use_case.TaskAdder;
import main.java.use_case.TaskGetter;
import java.time.LocalDateTime;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskController {
    private final AccessTodoData todoData = new AccessTodoData();
    private final TaskGetter taskGetter = new TaskGetter();
    private final TaskAdder taskAdder = new TaskAdder();

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
        taskAdder.addTask(taskName, timeNeeded, deadline, subTasks, todoData);
        return true; // TODO: return value should indicate success of data creation
    }

}
