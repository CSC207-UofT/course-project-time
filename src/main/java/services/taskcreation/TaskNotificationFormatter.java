package services.taskcreation;

import java.time.LocalDateTime;

public interface TaskNotificationFormatter {
    String formatMessage(String taskName, LocalDateTime deadline);
}
