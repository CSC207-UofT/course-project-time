package services.taskcreation;

import java.time.Duration;

public interface TaskWithNotificationModel extends TodoListTaskCreationModel{
    Duration getNotificationTimeInAdvance();
}
