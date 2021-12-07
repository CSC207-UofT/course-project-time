import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.updateentities.EventUpdater;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventUpdaterTest {

    EventUpdater eventUpdater;
    MockCalendarManager manager;

    @BeforeEach
    void setup() {
        manager = new MockCalendarManager();
        eventUpdater = new EventUpdater(manager);
    }

    @Test
    void updateName() {
        eventUpdater.updateName(10L, "mockname");
        assertTrue(manager.isUpdateNameSuccess());
    }

    @Test
    void updateStartTime() {
        eventUpdater.updateStartTime(11L, LocalTime.of(3, 14));
        assertTrue(manager.isUpdateStartTimeSuccess());
    }

    @Test
    void updateEndTime() {
        eventUpdater.updateEndTime(12L, LocalTime.of(12, 25));
        assertTrue(manager.isUpdateEndTimeSuccess());
    }

    @Test
    void addTag() {
        eventUpdater.addTag(13L, "mocktag");
        assertTrue(manager.isAddTagSuccess());
    }

    @Test
    void removeTag() {
        eventUpdater.removeTag(14L, "nottag");
        assertTrue(manager.isRemoveTagSuccess());
    }

    @Test
    void markEventAsCompleted() {
        eventUpdater.markEventAsCompleted(15L);
        assertTrue(manager.isMarkEventAsCompletedSuccess());
    }

    private static class MockCalendarManager implements CalendarManager {

        private boolean updateNameSuccess = false;
        private boolean updateStartTimeSuccess = false;
        private boolean updateEndTimeSuccess = false;
        private boolean addTagSuccess = false;
        private boolean removeTagSuccess = false;
        private boolean markEventAsCompletedSuccess = false;

        @Override
        public long addEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, HashSet<String> tags, LocalDate date) {
            return 0;
        }

        @Override
        public void markEventAsCompleted(long eventId) {
            if (eventId == 15L) {
                markEventAsCompletedSuccess = true;
            }
        }

        @Override
        public List<EventReader> getAllEvents() {
            return null;
        }

        @Override
        public void updateName(long id, String newName) {
            if (id == 10L && newName.equals("mockname")) {
                updateNameSuccess = true;
            }
        }

        @Override
        public void updateStartTime(long id, LocalTime newStartTime) {
            if (id == 11L && newStartTime.equals(LocalTime.of(3, 14))) {
                updateStartTimeSuccess = true;
            }
        }

        @Override
        public void updateEndTime(long id, LocalTime newEndTime) {
            if (id == 12L && newEndTime.equals(LocalTime.of(12, 25))) {
                updateEndTimeSuccess = true;
            }
        }

        @Override
        public void addTag(long id, String tag) {
            if (id == 13L && tag.equals("mocktag")) {
                addTagSuccess = true;
            }
        }

        @Override
        public void removeTag(long id, String tag) {
            if (id == 14L && tag.equals("nottag")) {
                removeTagSuccess = true;
            }
        }

        @Override
        public void loadEvents(String filePath) throws IOException {

        }

        @Override
        public void saveEvents(String savePath) throws IOException {

        }

        public boolean isUpdateNameSuccess() {
            return updateNameSuccess;
        }

        public boolean isUpdateStartTimeSuccess() {
            return updateStartTimeSuccess;
        }

        public boolean isUpdateEndTimeSuccess() {
            return updateEndTimeSuccess;
        }

        public boolean isAddTagSuccess() {
            return addTagSuccess;
        }

        public boolean isRemoveTagSuccess() {
            return removeTagSuccess;
        }

        public boolean isMarkEventAsCompletedSuccess() {
            return markEventAsCompletedSuccess;
        }
    }
}
