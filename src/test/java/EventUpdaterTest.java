import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import entity.dates.DateStrategy;
import entity.dates.TimeFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.strategybuilding.MultipleRuleFormBuilder;
import services.updateentities.EventUpdater;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

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
    void updateDateStrategy() {
        MultipleRuleFormBuilder formBuilder = new MultipleRuleFormBuilder();
        formBuilder.addSingleOccurrence(LocalTime.of(3, 14).atDate(LocalDate.now()));
        eventUpdater.updateDateStrategy(11L, formBuilder.getForm());
        assertTrue(manager.isUpdatedDateStrategySuccess());
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
        private boolean updatedDateStrategySuccess = false;
        private boolean addTagSuccess = false;
        private boolean removeTagSuccess = false;
        private boolean markEventAsCompletedSuccess = false;

        @Override
        public long addEvent(String eventName, DateStrategy strategy, Duration duration, Set<String> tags) {
            return 0;
        }

        @Override
        public long addEvent(long taskId, DateStrategy dateStrategy, Set<String> tags) {
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
        public void updateDateStrategy(long id, DateStrategy strategy) {
            List<TimeFrame> times = strategy.datesBetween(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), Duration.ofMinutes(5));
            if (id == 11L && times.size() > 0 && times.get(0).startTime.toLocalTime().equals(LocalTime.of(3, 14)))
                updatedDateStrategySuccess = true;
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

        public boolean isUpdatedDateStrategySuccess() {
            return updatedDateStrategySuccess;
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
