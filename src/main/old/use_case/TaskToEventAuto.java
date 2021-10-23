package main.old.use_case;

import main.old.entity.Calendar;
import main.old.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskToEventAuto {

    LocalDateTime getAvailableTime(Task task, Calendar calendar, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes);
}
