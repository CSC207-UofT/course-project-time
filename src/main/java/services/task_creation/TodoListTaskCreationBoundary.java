package services.task_creation;

public interface TodoListTaskCreationBoundary {

    void addTask(TodoListTaskCreationModel taskData);

    void completeTask(long taskId);

}
