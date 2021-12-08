package services.taskcreation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TaskWithNotificationData implements TaskWithNotificationModel{
    private final String taskName;
    private final Duration timeNeeded;
    private final LocalDateTime deadline;
    private final List<String> subtasks;
    private final Duration notificationTimeInAdvance;

    public TaskWithNotificationData(TodoListTaskCreationModel taskData, Duration notificationTimeInAdvance) {
        this.taskName = taskData.getName();
        this.timeNeeded = taskData.getDuration();
        this.deadline = taskData.getDeadline();
        this.subtasks = taskData.getSubtasks();
        this.notificationTimeInAdvance = notificationTimeInAdvance;
    }

    @Override
    public String getName() {
        return taskName;
    }

    @Override
    public Duration getDuration() {
        return timeNeeded;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public List<String> getSubtasks() {
        return subtasks;
    }

    @Override
    public Duration getNotificationTimeInAdvance() {
        return notificationTimeInAdvance;
    }
}
