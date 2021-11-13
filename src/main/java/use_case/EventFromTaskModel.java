package main.java.use_case;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

public interface EventFromTaskModel {

    int getTaskId();

    LocalDateTime getStartTime();

    HashSet<String> getTags();

    LocalDate getDates();

}
