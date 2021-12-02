package services.task_creation;

public interface TodoListTaskCreationBoundary {

    long addTask(TodoListTaskCreationModel taskData);

    void completeTask(long taskId);

}
