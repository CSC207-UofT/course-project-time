package services.task_creation;

public interface TaskAdding extends TodoListTaskCreationBoundary {
    long addTask(TodoListTaskCreationModel taskData);
    void completeTask(long taskId);
}
