package main.java.controller;

import main.java.use_case.AccessTodoData;
import main.java.use_case.TaskAdder;
import main.java.use_case.TaskGetter;
import java.time.LocalDateTime;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class TaskController {
    private final AccessTodoData todoData = new AccessTodoData();
    private final TaskGetter taskGetter = new TaskGetter();
    private final TaskAdder taskAdder = new TaskAdder();

    public List<HashMap<String, String>> getTasks() {
        return taskGetter.getTasks(todoData);
    }

    public boolean createTask(String taskName, Duration timeNeeded,
                              LocalDateTime deadline, List<String> subTasks) {
        taskAdder.addTaskWithDeadline(taskName, timeNeeded, deadline, todoData);
        return false;
    }

    public boolean createTask(String taskName, Duration timeNeeded) {
        taskAdder.addTaskWithoutDeadline(taskName, timeNeeded, todoData);
        return false;
    }
}
