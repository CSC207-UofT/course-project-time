import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.eventcreation.EventInfoFromReader;
import services.eventpresentation.EventGetter;
import services.eventpresentation.EventInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventGetterTest {

    EventGetter eventGetter;
    MockCalendarManager manager;

    Set<String> tags1;
    Set<String> tags2;
    Set<LocalDate> nov25Nov27;
    Set<LocalDate> nov25Nov26;
    EventReader e1;
    EventReader e2;
    List<EventReader> allEvents;

    @BeforeEach
    void setup() {
        tags1 = new HashSet<>();
        tags1.add("CSC207");
        tags1.add("Quiz");

        nov25Nov27 = new HashSet<>();
        nov25Nov27.add(LocalDate.of(2021, 11, 25));
        nov25Nov27.add(LocalDate.of(2021, 11, 27));

        e1 = new MockEventReader(1, "mock1", LocalTime.of(12, 0),
                LocalTime.of(14, 0), tags1, nov25Nov27);

        tags2 = new HashSet<>();
        tags2.add("CSC207");
        tags2.add("Project");

        nov25Nov26 = new HashSet<>();
        nov25Nov26.add(LocalDate.of(2021, 11, 25));
        nov25Nov26.add(LocalDate.of(2021, 11, 26));

        e2 = new MockEventReader(2, "mock2", LocalTime.of(14, 0),
                LocalTime.of(16, 0), tags2, nov25Nov26);

        allEvents = new ArrayList<>();
        allEvents.add(e1);
        allEvents.add(e2);

        manager = new MockCalendarManager();
        eventGetter = new EventGetter(manager);
    }

    @Test
    void getEvents() {
        MockEventReader eventReader1 = new MockEventReader(1L, "mock1",
                LocalTime.of(12, 0), LocalTime.of(14, 0), tags1, nov25Nov27);
        EventInfo eventInfo1 = new EventInfoFromReader(eventReader1);
        MockEventReader eventReader2 = new MockEventReader(2L, "mock2",
                LocalTime.of(14, 0), LocalTime.of(16, 0), tags2, nov25Nov26);
        EventInfo eventInfo2 = new EventInfoFromReader(eventReader2);

        List<EventInfo> expected = new ArrayList<>();
        expected.add(eventInfo1);
        expected.add(eventInfo2);

        List<EventInfo> actual = eventGetter.getEvents();

        assertEquals(expected.size(), actual.size());

        assertEquals(eventInfo1.getId(), actual.get(0).getId());
        assertEquals(eventInfo1.getName(), actual.get(0).getName());
        assertEquals(eventInfo1.getCompleted(), actual.get(0).getCompleted());
        assertEquals(eventInfo1.getDates(), actual.get(0).getDates());
        assertEquals(eventInfo1.getEndTime(), actual.get(0).getEndTime());
        assertEquals(eventInfo1.getStartTime(), actual.get(0).getStartTime());
        assertEquals(eventInfo1.getTags(), actual.get(0).getTags());

        assertEquals(eventInfo2.getId(), actual.get(1).getId());
        assertEquals(eventInfo2.getName(), actual.get(1).getName());
        assertEquals(eventInfo2.getCompleted(), actual.get(1).getCompleted());
        assertEquals(eventInfo2.getDates(), actual.get(1).getDates());
        assertEquals(eventInfo2.getEndTime(), actual.get(1).getEndTime());
        assertEquals(eventInfo2.getStartTime(), actual.get(1).getStartTime());
        assertEquals(eventInfo2.getTags(), actual.get(1).getTags());
    }

    @Test
    void getEventByName() {
        EventInfo expected = new EventInfoFromReader(e1);
        EventInfo actual = eventGetter.getEventByName("mock1");

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCompleted(), actual.getCompleted());
        assertEquals(expected.getDates(), actual.getDates());
        assertEquals(expected.getEndTime(), actual.getEndTime());
        assertEquals(expected.getStartTime(), actual.getStartTime());
        assertEquals(expected.getTags(), actual.getTags());
    }

    private class MockCalendarManager implements CalendarManager {

        @Override
        public long addEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, Set<String> tags, LocalDate date) {
            return 0;
        }

        @Override
        public long addEvent(long taskId, LocalDateTime startTime, Set<String> tags, LocalDate date) {
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
            return allEvents;
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
        public void loadEvents(String filePath) throws IOException {

        }

        @Override
        public void saveEvents(String savePath) throws IOException {

        }
    }

    private static class MockEventReader implements EventReader {
        final long id;
        final String name;
        final LocalTime startTime;
        final LocalTime endTime;
        final Set<String> tags;
        final Set<LocalDate> dates;

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
