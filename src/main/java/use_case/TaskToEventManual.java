package main.java.use_case;

import java.time.LocalDateTime;

public interface TaskToEventManual {

    boolean checkTimeAvailability(TaskInfo taskInfo, EventScheduler eventScheduler, LocalDateTime userSuggestedTime);
}
