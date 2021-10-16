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

class EventSchedulerTest {
    List<LocalDateTime> timesToIgnore;
    Duration taskDuration;
    Calendar calendar;
    EventScheduler eventScheduler;

    @BeforeEach
    void setUp() {
        timesToIgnore = new ArrayList<>();
        timesToIgnore.add(LocalDateTime.of(2021, 10, 14, 12, 0, 0));
        LocalDateTime startDate = LocalDateTime.of(2021, 10, 14, 14, 0, 0);
        LocalTime endTime = LocalTime.of(16, 0);
        taskDuration = Duration.ofHours(2);
        Task task = new Task("Math Homework");
        Event event = new Event(task, startDate, endTime);
        ArrayList<Event> eventlst = new ArrayList<>();
        eventlst.add(event);
        calendar = new Calendar("New Calendar", eventlst);
        eventScheduler = new EventScheduler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAvailableTime() {
        LocalDateTime expectedDateTime = LocalDateTime.of(2021, 10, 14, 16, 0, 0);
        assertEquals(expectedDateTime, eventScheduler.getAvailableTime(timesToIgnore, taskDuration, calendar));

    }

    @Test
    void checkAvailability() {
        LocalDateTime targetTime = LocalDateTime.of(2021, 10, 14, 11, 0, 0);
        assertTrue(eventScheduler.checkAvailability(targetTime, calendar, taskDuration));
    }
}