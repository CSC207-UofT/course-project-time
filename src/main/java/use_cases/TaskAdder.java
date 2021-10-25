package main.java.use_cases;

import main.java.entity_accessors.NewTaskData;
import main.java.entity_accessors.TaskManager;

public class TaskAdder implements TaskCreationBoundary {

    private final TaskManager taskManager;

    public TaskAdder(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public boolean createNewTask(NewTaskData taskData) {
        return taskManager.addTask(taskData);
    }

}
