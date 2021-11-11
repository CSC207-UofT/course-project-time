package main.java.interface_adapters;

import main.java.use_case.TaskInfo;

public interface TaskToEventAutoController {

    boolean suggestTimeToUser(TaskInfo task);
}
