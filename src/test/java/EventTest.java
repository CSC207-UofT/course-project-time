

import entity.Event;
import entity.Task;
import entity.dates.DateStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.strategies.SingleDateStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    Event event;
    DateStrategy strategy;

    final LocalDateTime startDate = LocalDateTime.of(2021, 10, 14, 14, 0, 0);
    @BeforeEach
    void setUp() {
        LocalTime endTime = LocalTime.of(16, 0);
        Task task = new Task(0, "Math Homework", Duration.between(startDate, endTime.atDate(startDate.toLocalDate())));
        strategy = new SingleDateStrategy(startDate);
        event = new Event(0, task.getId(), new SingleDateStrategy(startDate));

    }

    @Test
    void getStrategy() {
        assertEquals(strategy.toString(), event.getDateStrategy().toString());

    }

    @Test
    void setStrategy() {
        DateStrategy newStrategy = new SingleDateStrategy(startDate.plusDays(1));
        event.setDateStrategy(newStrategy);
        assertEquals(newStrategy, event.getDateStrategy());
    }


}