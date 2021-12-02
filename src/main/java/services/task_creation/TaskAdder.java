package services.task_creation;

import data_gateway.task.TodoListManager;

public class TaskAdder implements TodoListTaskCreationBoundary {

    private final TodoListManager todoListManager;

    public TaskAdder(TodoListManager todoListManager) {
        this.todoListManager = todoListManager;
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        return todoListManager.addTask(taskData);
    }

    @Override
    public void completeTask(long taskId) {
        todoListManager.completeTask(taskId);
    }
}

