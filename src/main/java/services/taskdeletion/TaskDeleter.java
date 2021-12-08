package services.taskdeletion;

import datagateway.task.TodoListManager;

public class TaskDeleter implements TaskDeletionBoundary {

    private final TodoListManager todoListManager;

    public TaskDeleter(TodoListManager todoListManager) {
        this.todoListManager = todoListManager;
    }

    @Override
    public void deleteTask(long taskId) {
        todoListManager.deleteTask(taskId);
    }
}
