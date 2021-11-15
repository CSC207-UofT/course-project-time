import console_app.event_adapters.CalendarEventData;
import data_gateway.CalendarManager;
import data_gateway.EventEntityManager;
import data_gateway.EventReader;
import data_gateway.EventToEventReader;
import entity.Event;
import services.Snowflake;
import services.event_creation.CalendarEventModel;
import services.event_creation.EventAdder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventAdderTest {
    EventAdder eventAdder;
    MockCalendarManager manager;
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
        manager = new MockCalendarManager(snowflake);
        eventAdder = new EventAdder(manager);

        eventAdder.addEvent(calendarEventData);
    }

    @Test
    void getAllEvents() {
        List<CalendarEventData> eventData = new ArrayList<>();
        eventData.add(calendarEventData);

        assertEquals(eventData, manager.getEventList());
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

        @Override
        public boolean markEventAsCompleted(long eventId) {
            return false;
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
