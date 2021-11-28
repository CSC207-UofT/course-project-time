package services.task_creation;

import data_gateway.TodoListManager;
import services.notification_system.NotificationAdder;

import java.time.Duration;

public class TaskAdderWithNotification implements TodoListTaskCreationBoundary {

    private final TaskAdder service;
    private final NotificationAdder notificationAdder;

    public TaskAdderWithNotification(TaskAdder service, NotificationAdder notificationAdder) {
        this.service = service;
        this.notificationAdder = notificationAdder;
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData, Duration notificationTimeInAdvance) {
        long taskId = service.addTask(taskData);
        notificationAdder.createNotification(taskData, taskId, notificationTimeInAdvance);
        return taskId;
    }

    @Override
    public void completeTask(long taskId) {
        service.completeTask(taskId);
    }
}
