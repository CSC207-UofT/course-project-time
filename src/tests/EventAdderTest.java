package tests;

import main.java.console_app.event_adapters.CalendarEventData;
import main.java.data_gateway.EventEntityManager;
import main.java.data_gateway.EventReader;
import main.java.data_gateway.EventToEventReader;
import main.java.services.Snowflake;
import main.java.services.event_creation.EventAdder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventAdderTest {
    EventAdder eventAdder;
    EventEntityManager eventEntityManager;
    CalendarEventData calendarEventData;
    Snowflake snowflake;

    @BeforeEach
    void setup() {
        snowflake = new Snowflake(0, 0, 0);
        calendarEventData = new CalendarEventData("Work on project",
                LocalDateTime.of(2021, 11, 15, 12, 0),
                LocalDateTime.of(2021, 11, 15, 14, 0),
                new HashSet<String>()
        );
        eventEntityManager = new EventEntityManager(snowflake);
        eventAdder = new EventAdder(eventEntityManager);

        eventAdder.addEvent(calendarEventData);
    }

    @Test
    void getAllEvents() {
        List<EventReader> allEvent = new ArrayList<>();

    }
}
