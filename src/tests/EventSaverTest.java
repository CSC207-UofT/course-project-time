package tests;

import main.java.data_gateway.CalendarManager;
import main.java.data_gateway.EventReader;
import main.java.services.event_creation.CalendarEventModel;
import main.java.services.event_creation.EventSaver;
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

    private class MockCalendarManager implements CalendarManager {
        String savedEvent;

        @Override
        public boolean addEvent(CalendarEventModel eventData) {
            return false;
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
            savedEvent = savePath;
        }

        public String getSavedEvent() {
            return savedEvent;
        }
    }
}
