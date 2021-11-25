package services.task_creation;

public interface TaskAdding extends TodoListTaskCreationBoundary {
    void addTask(TodoListTaskCreationModel taskData);
    void completeTask(long taskId);
}
