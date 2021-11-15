package tests;

import main.java.entity.Calendar;
import main.java.entity.Event;
import main.java.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarTest {
    List<LocalDateTime> timesToIgnore;
    Duration taskDuration;
    Calendar calendar;

    @BeforeEach
    void setUp() {
        timesToIgnore = new ArrayList<>();
        timesToIgnore.add(LocalDateTime.of(2021, 10, 14, 12, 0, 0));
        LocalDateTime startDate = LocalDateTime.of(2021, 10, 14, 14, 0, 0);
        LocalTime endTime = LocalTime.of(16, 0);
        taskDuration = Duration.ofHours(2);
        Task task = new Task(0, "Math Homework");
        Event event = new Event(0, task, startDate, endTime);
        List<Event> eventlst = new ArrayList<>();
        eventlst.add(event);
        calendar = new Calendar("New Calendar", eventlst);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("New Calendar", calendar.getName());
    }

}