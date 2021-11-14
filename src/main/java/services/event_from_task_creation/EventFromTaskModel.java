package main.java.services.event_from_task_creation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public interface EventFromTaskModel {

    int getTaskId();

    LocalDateTime getStartTime();

    HashSet<String> getTags();

    LocalDate getDates();

}