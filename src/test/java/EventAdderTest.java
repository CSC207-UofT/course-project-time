import consoleapp.eventadapters.CalendarEventData;
import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import entity.dates.DateStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.eventcreation.EventAdder;
import services.strategybuilding.DatesForm;
import services.strategybuilding.Rule;
import services.strategybuilding.Rules;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventAdderTest {
    EventAdder eventAdder;
    MockCalendarManager manager;
    CalendarEventData calendarEventData;

    final LocalDateTime tomorrowNoon = LocalDate.now().plusDays(1).atTime(LocalTime.NOON);

    @BeforeEach
    void setup() {

        calendarEventData = new CalendarEventData("Work on project",
                Duration.ofHours(2),
                new MockDatesForm(tomorrowNoon),
                new HashSet<>()
        );
        manager = new MockCalendarManager();
        eventAdder = new EventAdder(manager);

    }

    @Test
    void addEvent() {
        eventAdder.addEvent(calendarEventData);
        EventBuffer output = manager.output;
        assertEquals("Work on project", output.eventName);
        assertEquals(calendarEventData.getDuration(), output.duration);
        assertEquals(0, output.tags.size());
    }

    private static class MockDatesForm implements DatesForm {

        private final LocalDateTime date;

        private MockDatesForm(LocalDateTime date) {
            this.date = date;
        }

        @Override
        public Iterator<Rule> iterator() {
            List<Rule> rules = new ArrayList<>();
            rules.add(new Rules.StartSingleDateRule(date));
            return rules.iterator();
        }

        @Override
        public void addRule(Rule rule) {

        }
    }

    private static class EventBuffer {
        public final String eventName;
        public final DateStrategy strategy;
        public final Duration duration;
        public final Set<String> tags;

        public EventBuffer(String eventName, DateStrategy strategy, Duration duration, Set<String> tags) {
            this.eventName = eventName;
            this.strategy = strategy;
            this.duration = duration;
            this.tags = tags;
        }
    }

    private static class MockCalendarManager implements CalendarManager {

        public EventBuffer output;

        @Override
        public long addEvent(String eventName, DateStrategy strategy, Duration duration, Set<String> tags) {
            output = new EventBuffer(eventName, strategy, duration, tags);
            return 0L;
        }

        @Override
        public long addEvent(long taskId, DateStrategy dateStrategy, Set<String> tags) {
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
        public void updateDateStrategy(long id, DateStrategy strategy) {

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

        }
    }
}
