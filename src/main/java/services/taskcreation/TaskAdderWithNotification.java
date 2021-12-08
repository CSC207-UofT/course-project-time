package services.taskcreation;

import services.notification.NotificationAdder;
import services.notification.NotificationCreationModel;
import services.notification.NotificationData;
import services.notification.NotificationFormat;

import java.time.LocalDateTime;

public class TaskAdderWithNotification implements TodoListTaskCreationBoundary {

    private final TodoListTaskCreationBoundary service;
    private final NotificationAdder notificationAdder;
    private final NotificationFormat notificationFormat;

    public TaskAdderWithNotification(TodoListTaskCreationBoundary service, NotificationAdder notificationAdder, NotificationFormat notificationFormat) {
        this.service = service;
        this.notificationAdder = notificationAdder;
        this.notificationFormat = notificationFormat;
    }

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        long taskId = service.addTask(taskData);
        String taskName = taskData.getName();
        LocalDateTime deadline = taskData.getDeadline();
        String message = notificationFormat.formatTaskNotificationMessage(taskName, deadline);

        // TODO: get notificationTimeInAdvance, notificationDateTime
        NotificationCreationModel notificationData = new NotificationData(
                taskId,
                null,
                null,
                message);

        notificationAdder.addNotification(notificationData);
        return taskId;
    }
}
