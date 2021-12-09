package services.taskcreation;

public class TaskAdderWithNotification implements TodoListTaskCreationBoundary {

    private final TodoListTaskCreationBoundary service;

    public TaskAdderWithNotification(TodoListTaskCreationBoundary service) {
        this.service = service;
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        return service.addTask(taskData);
    }
}
