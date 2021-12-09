package services.taskcreation;

import services.notification.NotificationAdder;
import services.notification.NotificationCreationModel;
import services.notification.NotificationData;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskAdderWithNotification implements TaskWithNotificationCreationBoundary {

    private final TodoListTaskCreationBoundary service;
    private final NotificationAdder notificationAdder;
    private final TaskNotificationFormatter notificationFormatter;

    public TaskAdderWithNotification(TodoListTaskCreationBoundary service,
                                     NotificationAdder notificationAdder,
                                     TaskNotificationFormatter notificationFormatter) {
        this.service = service;
        this.notificationAdder = notificationAdder;
        this.notificationFormatter = notificationFormatter;
    }

    @Override
    public long addTask(TaskWithNotificationModel taskData) {
        long taskId = service.addTask(taskData);
        String taskName = taskData.getName();
        LocalDateTime deadline = taskData.getDeadline();
        String message = notificationFormatter.formatMessage(taskName, deadline);

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

    @Override
    public long addTask(TodoListTaskCreationModel taskData) {
        return service.addTask(taskData);
    }
}
