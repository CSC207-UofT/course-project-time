package main.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {
    List<LocalDateTime> timesToIgnore;
    Duration taskDuration;
    Calendar calendar;

    @BeforeEach
    void setUp() {
        timesToIgnore = new ArrayList<>();
        timesToIgnore.add(LocalDateTime.of(2021, 10, 14, 12, 0, 0));
        taskDuration = Duration.ofHours(2);
        ArrayList<Event> eventList = new ArrayList<>();
        calendar = new Calendar("New Calendar", eventList);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getName() {
        assertEquals("New Calendar", calendar.getName());
    }

}