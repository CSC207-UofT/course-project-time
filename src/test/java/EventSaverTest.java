import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import services.eventcreation.EventSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
        public long addEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, Set<String> tags, LocalDate date) {
            return 0;
        }

        @Override
        public long addEvent(long taskId, LocalDateTime startTime, Set<String> tags, LocalDate date) {
            return 0;
        }

        @Override
        public void markEventAsCompleted(long eventId) {
        }

        @Override
        public List<EventReader> getAllEvents() {
            return null;
        }

        @Override
        public void updateName(long id, String newName) {

        }

        @Override
        public void updateStartTime(long id, LocalTime newStartTime) {

        }

        @Override
        public void updateEndTime(long id, LocalTime newEndTime) {

        }

        @Override
        public void addTag(long id, String tag) {

        }

        @Override
        public void removeTag(long id, String tag) {

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
