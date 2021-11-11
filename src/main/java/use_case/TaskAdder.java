package main.java.use_case;

import main.java.entity.Task;
import main.java.entity_gateway.TodoListManager;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskAdder implements TodoListTaskCreationBoundary {

    private final TodoListManager todoListManager;

    public TaskAdder(TodoListManager todoListManager) {
        this.todoListManager = todoListManager;
    }

    @Override
    public int addTask(TodoListTaskCreationModel taskData) {
        return todoListManager.addTask(taskData);
    }
}

