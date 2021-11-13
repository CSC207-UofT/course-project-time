package main.java.console_app.task_to_event_adapters;

import main.java.services.task_presentation.TaskInfo;

public interface TaskToEventAutoController {

    boolean suggestTimeToUser(TaskInfo task);
}
