import data_gateway.CalendarManager;
import data_gateway.EventReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.event_creation.CalendarEventModel;
import services.event_from_task_creation.EventScheduler;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventSchedulerTest {
    EventScheduler eventScheduler;
    CalendarManager manager;
    List<EventReader> events = new ArrayList<>();

    @BeforeEach
    void setup() {
        manager = new MockCalendarManager(events);
        eventScheduler = new EventScheduler(manager);

        Set<String> tags1 = new HashSet<>();
        tags1.add("CSC207");
        tags1.add("Quiz");

        Set<LocalDate> nov25Nov27 = new HashSet<>();
        nov25Nov27.add(LocalDate.of(2021, 11, 25));
        nov25Nov27.add(LocalDate.of(2021, 11, 27));

        MockEventReader reader1 = new MockEventReader(207L, "CSC207 Quiz", LocalTime.of(12, 7),
                LocalTime.of(14, 7), tags1, nov25Nov27);

        Set<String> tags2 = new HashSet<>();
        tags2.add("CSC207");
        tags2.add("Project");

        Set<LocalDate> nov25Nov26 = new HashSet<>();
        nov25Nov26.add(LocalDate.of(2021, 11, 25));
        nov25Nov26.add(LocalDate.of(2021, 11, 26));

        MockEventReader reader2 = new MockEventReader(208L, "CSC207 Project", LocalTime.of(16, 0),
                LocalTime.of(17, 0), tags2, nov25Nov26);

        events.add(reader1);
        events.add(reader2);
    }

    @Test
    public void checkAvailabilityTrue() {
        boolean actual = eventScheduler.checkAvailability(LocalDateTime.of(
                2021, 11, 25, 14, 30), Duration.ofHours(1));
        assertTrue(actual);
    }

    @Test
    public void checkAvailabilityFalse() {
        boolean actual = eventScheduler.checkAvailability(LocalDateTime.of(
                2021, 11, 25, 15, 30), Duration.ofHours(1));
        assertFalse(actual);
    }

    @Test
    public void isAvailableTrue() {
        boolean actual = eventScheduler.isAvailable(LocalTime.of(14, 30), Duration.ofHours(1),
                LocalDate.of(2021, 11, 25));
        assertTrue(actual);
    }

    @Test
    public void isAvailableFalse() {
        boolean actual = eventScheduler.isAvailable(LocalTime.of(
                15, 30), Duration.ofHours(1), LocalDate.of(2021, 11, 25));
        assertFalse(actual);
    }

    @Test
    public void getAvailableTimeNoIgnore() {
        LocalDateTime expected = LocalDateTime.of(2021, 11, 25, 14, 7);
        LocalDateTime actual = eventScheduler.getAvailableTime(new ArrayList<>(), Duration.ofHours(1));
        assertEquals(expected, actual);
    }

    @Test
    public void getAvailableTimeIgnore() {
        List<LocalDateTime> timesToIgnore = new ArrayList<>();
        timesToIgnore.add(LocalDateTime.of(2021, 11, 25, 14, 7));

        LocalDateTime expected = LocalDateTime.of(2021, 11, 25, 17, 0);
        LocalDateTime actual = eventScheduler.getAvailableTime(timesToIgnore, Duration.ofHours(1));
        assertEquals(expected, actual);
    }

    private static class MockCalendarManager implements CalendarManager {
        List<EventReader> events;

        public MockCalendarManager(List<EventReader> events) {
            this.events = events;
        }

        @Override
        public long addEvent(CalendarEventModel eventData) {
            return 0L;
        }

        @Override
        public void markEventAsCompleted(long eventId) {

        }

        @Override
        public List<EventReader> getAllEvents() {
            return events;
        }

        @Override
        public void loadEvents(String filePath) throws IOException {

        }

        @Override
        public void saveEvents(String savePath) throws IOException {

        }
    }

    private class MockEventReader implements EventReader {
        long id;
        String name;
        LocalTime startTime;
        LocalTime endTime;
        Set<String> tags;
        Set<LocalDate> dates;

        public MockEventReader(long id, String name, LocalTime startTime, LocalTime endTime,
                               Set<String> tags, Set<LocalDate> dates) {
            this.id = id;
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
            this.tags = tags;
            this.dates = dates;
        }

        @Override
        public long getId() {
            return this.id;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public LocalTime getStartTime() {
            return this.startTime;
        }

        @Override
        public LocalTime getEndTime() {
            return this.endTime;
        }

        @Override
        public Set<String> getTags() {
            return this.tags;
        }

        @Override
        public Set<LocalDate> getDates() {
            return this.dates;
        }

        @Override
        public boolean getCompleted() {
            return false;
        }
    }
}
