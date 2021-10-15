package main.java.controllers;

import main.java.ManageTodoData;
import main.java.TaskGetter;
import java.time.LocalDateTime;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskController {
    private static final ManageTodoData todoData = new ManageTodoData();
    private static final TaskGetter taskGetter = new TaskGetter(todoData);

    public List<HashMap<String, String>> getTasks() {
        return new ArrayList<>(); // todo add body
    }

    public boolean createTask(String taskName, Duration timeNeeded,
                              LocalDateTime deadline, List<String> subTasks) {

        return false;
    }
}
