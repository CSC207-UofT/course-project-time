package main.java.interface_adapters;

import main.java.use_case.TaskOutputDTO;

public interface TaskToEventAutoController {

    boolean suggestTimeToUser(TaskOutputDTO task);
}
