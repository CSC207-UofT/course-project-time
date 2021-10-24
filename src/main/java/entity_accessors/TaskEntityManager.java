package main.java.entity_accessors;

import main.java.entities.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskEntityManager implements TaskManager {

    private final Map<Integer, Task> taskMap;

    public TaskEntityManager() {
        taskMap = new HashMap<>();
    }

    public boolean addTask(NewTaskData taskData) {
        Task task = new Task(taskData.taskName, taskData.timeNeeded, taskData.deadline, taskData.subTasks);
        int taskId = task.getId();
        if (taskMap.containsKey(taskId)) {
            return false;
        } else {
            taskMap.put(taskId, task);
            return true;
        }
    }
}
