package main.java.use_case;

import main.java.entity.Calendar;
import main.java.entity.Task;

import java.time.LocalDateTime;

public interface TaskToEventManual {

    boolean checkTimeAvailability(Task task, AccessCalendarData accessCalendarData, EventScheduler eventScheduler, LocalDateTime userSuggestedTime);
}
