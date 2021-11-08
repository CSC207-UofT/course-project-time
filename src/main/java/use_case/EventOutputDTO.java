package main.java.use_case;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface EventOutputDTO {
    String getName();

    LocalTime getStartTime();

    LocalTime getEndTime();

    Set<String> getTags();

    Set<LocalDate> getDates();
}
