package main.java.use_case;

import main.java.entity_gateway.TodoListManager;

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
