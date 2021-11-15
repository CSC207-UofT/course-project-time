package main.java.data_gateway;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public interface EventReader {
    long getId();

    String getName();

    LocalTime getStartTime();

    LocalTime getEndTime();

    Set<String> getTags();

    Set<LocalDate> getDates();
}
