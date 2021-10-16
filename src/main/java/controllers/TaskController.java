package main.java.controllers;

import main.java.ManageTodoData;
import main.java.TaskAdder;
import main.java.TaskGetter;
import java.time.LocalDateTime;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskController {
    private ManageTodoData todoData = new ManageTodoData();
    private TaskGetter taskGetter = new TaskGetter();
    private TaskAdder taskAdder = new TaskAdder();

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
