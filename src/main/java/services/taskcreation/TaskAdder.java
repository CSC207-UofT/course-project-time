package services.taskcreation;

import datagateway.task.TodoListManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskAdder implements TodoListTaskCreationBoundary {

    private final TodoListManager todoListManager;

    public TaskAdder(TodoListManager todoListManager) {
        this.todoListManager = todoListManager;
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        String name = taskData.getName();
        Duration duration = taskData.getDuration();
        LocalDateTime deadline = taskData.getDeadline();
        List<String> subtasks = taskData.getSubtasks();
        return todoListManager.addTask(name, duration, deadline, subtasks);
    }

}

