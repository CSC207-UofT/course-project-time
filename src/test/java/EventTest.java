

import entity.Event;
import entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    LocalDateTime startDate;
    LocalTime endTime;
    Task task;
    Event event;

    @BeforeEach
    void setUp() {
        startDate = LocalDateTime.of(2021, 10, 14, 14, 0, 0);
        endTime = LocalTime.of(16, 0);
        task = new Task(0, "Math Homework");
        event = new Event(0, task, startDate, endTime);

    }

    @Test
    void getEventName() {
        assertEquals("Math Homework", event.getEventName());
    }

    @Test
    void getStartTime() {
        assertEquals(startDate.toLocalTime(), event.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(endTime, event.getEndTime());
    }

    @Test
    void getTask() {
        assertEquals(task, event.getTask());
    }

    @Test
    void setEventName() {
        event.setEventName("CSC207 Project");
        assertEquals("CSC207 Project", event.getEventName());
    }

    @Test
    void setStartTime() {
        LocalDateTime newStartDate = LocalDateTime.of(2021, 10, 14, 15, 30, 0);
        event.setStartTime(newStartDate.toLocalTime());
        assertEquals(newStartDate.toLocalTime(), event.getStartTime());

    }

    @Test
    void setEndTime() {
        LocalTime newEndTime = LocalTime.of(17, 30);
        event.setEndTime(newEndTime);
        assertEquals(newEndTime, event.getEndTime());
    }


}