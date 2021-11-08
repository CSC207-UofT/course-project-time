package main.java.use_case;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface EventFromTaskDTO {

    int getTaskId();

    LocalDateTime getStartTime();

    Set<String> getTags();

    LocalDate getDates();

}
