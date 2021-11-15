package tests;

import main.java.console_app.event_adapters.CalendarEventData;
import main.java.data_gateway.CalendarManager;
import main.java.data_gateway.EventReader;
import main.java.services.Snowflake;
import main.java.services.event_creation.CalendarEventCreationBoundary;
import main.java.services.event_creation.CalendarEventModel;
import main.java.services.event_creation.EventAdder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarEventCreationBoundaryTest {
    CalendarEventCreationBoundary calendarEventCreationBoundary;
    Snowflake snowflake;
    MockCalendarManager calendarManager;

    @BeforeEach
    void setUp() {
        calendarEventCreationBoundary = new EventAdder(calendarManager);
        snowflake = new Snowflake(1, 2, 3);
        calendarManager = new MockCalendarManager(snowflake);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addEvent() {
        LocalDateTime startTime = LocalDateTime.of(2021, 11, 15, 12, 00);
        LocalDateTime endTime = LocalDateTime.of(2021, 11, 15, 14, 00);
        HashSet<String> tags = new HashSet<>();
        tags.add("Math");
        CalendarEventData calendarEventData = new CalendarEventData("Homework", startTime, endTime, tags);
        assertTrue(calendarEventCreationBoundary.addEvent(calendarEventData));
    }

    private class MockCalendarManager implements CalendarManager {
        private final Snowflake snowflake;
        private final ArrayList<CalendarEventData> eventList;

        public MockCalendarManager(Snowflake snowflake) {
            this.snowflake = snowflake;
            this.eventList = new ArrayList<>();
        }

        @Override
        public boolean addEvent(CalendarEventModel eventData) {
            return eventList.add((CalendarEventData) eventData);
        }

        public ArrayList<CalendarEventData> getEventList() {
            return eventList;
        }

        @Override
        public List<EventReader> getAllEvents() {
            return null;
        }

        @Override
        public void loadEvents(String filePath) throws IOException {

        }

        @Override
        public void saveEvents(String savePath) throws IOException {

        }
    }
}