package main.old.use_case;

import main.old.entity.Task;

import java.time.LocalDateTime;

public interface TaskToEventManual {

    boolean checkTimeAvailability(Task task, AccessCalendarData accessCalendarData, EventScheduler eventScheduler, LocalDateTime userSuggestedTime);
}
