package tests;

import main.java.console_app.task_to_event_adapters.EventFromTaskId;
import main.java.services.event_from_task_creation.EventFromTaskModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EventFromTaskModelTest {
    EventFromTaskModel eventFromTaskModel;

    @BeforeEach
    void setUp() {
        HashSet<String> tags = new HashSet<>();
        LocalDateTime startTime = LocalDateTime.of(2021, 11, 15, 12, 00);
        eventFromTaskModel = new EventFromTaskId(123, startTime, tags);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTaskId() {
        int actual = 123;
        assertEquals(actual, eventFromTaskModel.getTaskId());
    }

    @Test
    void getStartTime() {
        LocalDateTime actual = LocalDateTime.of(2021, 11, 15, 12, 00);
        assertEquals(actual, eventFromTaskModel.getStartTime());
    }

    @Test
    void getTags() {
        HashSet<String> tags = new HashSet<>();
        assertEquals(tags, eventFromTaskModel.getTags());
    }

}