package services.task_creation;

public interface TodoListTaskCreationBoundary {

    int addTask(TodoListTaskCreationModel taskData);

    boolean completeTask(long taskId);

}
