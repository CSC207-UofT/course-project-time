import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import entity.dates.DateStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.eventpresentation.CalendarEventPresenter;
import services.eventpresentation.EventInfo;
import services.eventpresentation.EventOutputter;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventOutputterTest {

    EventOutputter eventOutputter;
    MockCalendarManager manager;
    MockCalendarEventPresenter presenter;

    @BeforeEach
    void setup() {
        manager = new MockCalendarManager();
        presenter = new MockCalendarEventPresenter();
        eventOutputter = new EventOutputter(manager, presenter);
    }

    @Test
    void presentAllEvents() {
        eventOutputter.presentAllEvents();
        assertTrue(presenter.isPresentAllEventsSuccess());
    }

    private static class MockCalendarManager implements CalendarManager {

        @Override
        public long addEvent(String eventName, DateStrategy strategy, Duration duration, Set<String> tags) {
            return 0;
        }

        @Override
        public long addEvent(long taskId, DateStrategy dateStrategy, Set<String> tags) {
            return 0;
        }

        @Override
        public void deleteEvent(long eventId) {

        }

        @Override
        public void markEventAsCompleted(long eventId) {

        }

        @Override
        public List<EventReader> getAllEvents() {
            return new ArrayList<>();
        }

        @Override
        public void updateName(long id, String newName) {

        }

        @Override
        public void updateDateStrategy(long id, DateStrategy strategy) {

        }

        @Override
        public void updateDuration(long id, Duration duration) {

        }

        @Override
        public void addTag(long id, String tag) {

        }

        @Override
        public void removeTag(long id, String tag) {

        }

        @Override
        public void loadEvents(String filePath) throws IOException {

        }

        @Override
        public void saveEvents(String savePath) throws IOException {

        }
    }

    private static class MockCalendarEventPresenter implements CalendarEventPresenter {

        private boolean presentAllEventsSuccess = false;

        @Override
        public void presentAllEvents(List<EventInfo> eventInfos) {
            if (eventInfos.equals(new ArrayList<>())) {
                presentAllEventsSuccess = true;
            }
        }

        public boolean isPresentAllEventsSuccess() {
            return presentAllEventsSuccess;
        }
    }
}
