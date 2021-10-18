package tests;

import main.java.entity.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Duration timeNeeded;
    LocalDateTime deadline;
    Task task;

    @BeforeEach
    void setUp() {
        timeNeeded = Duration.ofHours(2);
        deadline = LocalDateTime.of(2021, 10, 15, 23, 59, 59);
        task = new Task("CSC207 project", timeNeeded, deadline);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getTaskName() {
        assertEquals("CSC207 project", task.getTaskName());
    }

    @Test
    void getCompleted() {
        assertFalse(task.getCompleted());
    }

    @Test
    void getTimeNeeded() {
        assertEquals(timeNeeded, task.getTimeNeeded());
    }

    @Test
    void getDeadline() {
        assertEquals(deadline, task.getDeadline());
    }

    @Test
    void setTaskName() {
        task.setTaskName("Math assignment");
        assertEquals("Math assignment", task.getTaskName());
    }

    @Test
    void setCompleted() {
        task.setCompleted(true);
        assertTrue(task.getCompleted());
    }

    @Test
    void setTimeNeeded() {
        Duration newTimeneeded = Duration.ofHours(4);
        task.setTimeNeeded(newTimeneeded);
        assertEquals(newTimeneeded, task.getTimeNeeded());
    }

    @Test
    void setDeadline() {
        LocalDateTime newDeadline = LocalDateTime.of(2021, 10, 14, 11, 59, 59);
        task.setDeadline(newDeadline);
        assertEquals(newDeadline, task.getDeadline());
    }


}