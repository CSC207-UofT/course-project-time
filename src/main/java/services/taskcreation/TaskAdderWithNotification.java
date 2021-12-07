package services.taskcreation;

import services.notification.NotificationAdder;

public class TaskAdderWithNotification implements TodoListTaskCreationBoundary {

    private final TodoListTaskCreationBoundary service;
    private final NotificationAdder notificationAdder;

    public TaskAdderWithNotification(TodoListTaskCreationBoundary service, NotificationAdder notificationAdder) {
        this.service = service;
        this.notificationAdder = notificationAdder;
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        long taskId = service.addTask(taskData);
        notificationAdder.createNotification(taskData, taskId);
        return taskId;
    }
}
