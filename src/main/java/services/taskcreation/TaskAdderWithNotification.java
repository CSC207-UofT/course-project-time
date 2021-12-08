package services.taskcreation;

import services.notification.NotificationAdder;
import services.notification.NotificationCreationModel;
import services.notification.NotificationData;
import services.notification.NotificationFormatter;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskAdderWithNotification implements TaskWithNotificationCreationBoundary {

    private final TodoListTaskCreationBoundary service;
    private final NotificationAdder notificationAdder;
    private final NotificationFormatter notificationFormatter;

    public TaskAdderWithNotification(TodoListTaskCreationBoundary service, NotificationAdder notificationAdder, NotificationFormatter notificationFormatter) {
        this.service = service;
        this.notificationAdder = notificationAdder;
        this.notificationFormatter = notificationFormatter;
    }

    @Override
    public long addTask(TaskWithNotificationModel taskData) {
        long taskId = service.addTask(taskData);
        String taskName = taskData.getName();
        LocalDateTime deadline = taskData.getDeadline();
        String message = notificationFormatter.formatTaskNotificationMessage(taskName, deadline);

        Duration notificationTimeInAdvance = taskData.getNotificationTimeInAdvance();
        LocalDateTime notificationDateTime = deadline.plus(notificationTimeInAdvance);

        NotificationCreationModel notificationData = new NotificationData(
                taskId,
                notificationTimeInAdvance,
                notificationDateTime,
                message);

        notificationAdder.addNotification(notificationData);
        return taskId;
    }
}
