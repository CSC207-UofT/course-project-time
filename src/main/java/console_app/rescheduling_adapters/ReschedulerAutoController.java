package main.java.interface_adapters;

import main.java.use_case.EventInfo;
import main.java.use_case.TaskInfo;

public interface ReschedulerAutoController {
    boolean rescheduleTime(EventInfo eventInfo);
}
