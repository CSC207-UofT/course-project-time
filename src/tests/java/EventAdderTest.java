import console_app.event_adapters.CalendarEventData;
import data_gateway.CalendarManager;
import data_gateway.EventReader;
import services.event_creation.CalendarEventModel;
import services.event_creation.EventAdder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventAdderTest {
    EventAdder eventAdder;
    MockCalendarManager manager;
    CalendarEventData calendarEventData;

    @BeforeEach
    void setup() {
        calendarEventData = new CalendarEventData("Work on project",
                LocalDateTime.of(2021, 11, 15, 12, 0),
                LocalDateTime.of(2021, 11, 15, 14, 0),
                new HashSet<>()
        );
        manager = new MockCalendarManager();
        eventAdder = new EventAdder(manager);

        eventAdder.addEvent(calendarEventData);
    }

    @Test
    void getAllEvents() {
        List<CalendarEventData> eventData = new ArrayList<>();
        eventData.add(calendarEventData);

        assertEquals(eventData, manager.getEventList());
    }

    private static class MockCalendarManager implements CalendarManager {
        private final ArrayList<CalendarEventData> eventList;

        public MockCalendarManager() {
            this.eventList = new ArrayList<>();
        }

        @Override
        public void addEvent(CalendarEventModel eventData) {
            eventList.add((CalendarEventData) eventData);
        }

        @Override
        public void markEventAsCompleted(long eventId) {
        }

        public ArrayList<CalendarEventData> getEventList() {
            return eventList;
        }

        @Override
        public List<EventReader> getAllEvents() {
            return null;
        }

        @Override
        public void loadEvents(String filePath) {

        }

        @Override
        public void saveEvents(String savePath) {

        }
    }
}
