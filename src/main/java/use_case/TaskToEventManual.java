package main.java.use_case;

import java.time.LocalDateTime;

public interface TaskToEventManual {

    boolean checkTimeAvailability(TaskOutputDTO taskOutputDTO, EventScheduler eventScheduler, LocalDateTime userSuggestedTime);
}
