package main.java.use_case.notification;

import java.time.Duration;

public class TaskNotificationCreationBoundary extends NotificationCreationBoundary {
    private final int taskId;
    private final Duration notificationDurationInAdvance;

    public TaskNotificationCreationBoundary(int taskId, Duration notificationTimeInAdvance) {
        this.taskId = taskId;
        this.notificationDurationInAdvance = notificationTimeInAdvance;
    }

    public int getTaskId() {
        return taskId;
    }

    public Duration getNotificationDurationInAdvance() {
        return notificationDurationInAdvance;
    }
}
