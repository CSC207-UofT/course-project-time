package main.java.use_case;

import java.time.LocalDateTime;

public interface TaskToEventManual {

    boolean checkTimeAvailability(TaskInfo task, AccessCalendarData accessCalendarData, EventScheduler eventScheduler, LocalDateTime userSuggestedTime);
}
