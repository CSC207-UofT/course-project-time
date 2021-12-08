package services.taskcreation;

import java.time.LocalDateTime;

public class BasicTaskNotificationFormatter implements TaskNotificationFormatter{
    /**
     * Generate a task notification message with the format
     *          You have an upcoming task <task name> due on <deadline>
     * @param taskName the name of the task
     * @param deadline the deadline of the task
     * @return the task notification message
     */
    @Override
    public String formatMessage(String taskName, LocalDateTime deadline) {
        return "You have an upcoming task due at" + deadline.toString();
    }
}
