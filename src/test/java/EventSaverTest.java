import data_gateway.CalendarManager;
import data_gateway.EventReader;
import services.event_creation.CalendarEventModel;
import services.event_creation.EventSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventSaverTest {
    EventSaver eventSaver;
    MockCalendarManager manager;

    @BeforeEach
    void setup() {
        manager = new MockCalendarManager();
        eventSaver = new EventSaver(manager);
    }

    @Test
    void saveEventData() throws IOException {
        eventSaver.saveEventData("CSC207");
        String expected = "CSC207";
        String actual = manager.getSavedEvent();
        assertEquals(expected, actual);
    }

    private static class MockCalendarManager implements CalendarManager {
        String savedEvent;

        @Override
        public void addEvent(CalendarEventModel eventData) {
        }

        @Override
        public void markEventAsCompleted(long eventId) {
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
            savedEvent = savePath;
        }

        public String getSavedEvent() {
            return savedEvent;
        }
    }
}