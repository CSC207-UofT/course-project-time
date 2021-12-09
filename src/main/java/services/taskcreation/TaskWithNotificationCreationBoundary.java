package services.taskcreation;

public interface TaskWithNotificationCreationBoundary extends TodoListTaskCreationBoundary{
    long addTask(TaskWithNotificationModel taskData);
}
