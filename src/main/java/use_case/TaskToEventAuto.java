package main.java.use_case;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskToEventAuto {

    LocalDateTime getAvailableTime(TaskOutputDTO taskOutputDTO, EventScheduler eventScheduler, List<LocalDateTime> unwantedTimes);
}
