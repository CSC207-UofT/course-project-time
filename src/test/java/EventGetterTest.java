import data_gateway.event.CalendarManager;
import data_gateway.event.EventReader;
import entity.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.event_creation.EventInfoFromReader;
import services.event_presentation.CalendarEventPresenter;
import services.event_presentation.EventGetter;
import services.event_presentation.EventInfo;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventGetterTest {

    EventGetter eventGetter;
    MockCalendarManager manager;
    MockCalendarEventPresenter presenter;

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
        presenter = new MockCalendarEventPresenter();
        eventGetter = new EventGetter(manager, presenter);
    }

    @Test
    void getEvent() {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("name", "mock1");
        map1.put("start", LocalTime.of(12, 0).toString());
        map1.put("end", LocalTime.of(14, 0).toString());
        map1.put("tags", tags1.toString());
        map1.put("dates", nov25Nov27.toString());

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("name", "mock2");
        map2.put("start", LocalTime.of(14, 0).toString());
        map2.put("end", LocalTime.of(16, 0).toString());
        map2.put("tags", tags2.toString());
        map2.put("dates", nov25Nov26.toString());

        List<HashMap<String, String>> expected = new ArrayList<>();
        expected.add(map1);
        expected.add(map2);

        List<HashMap<String, String>> actual = eventGetter.getEvents();

        assertEquals(expected, actual);
    }

    @Test
    void presentAllEvents() {
        eventGetter.presentAllEvents();
        assertTrue(presenter.isPresentAllEventsSuccess());
    }

    @Test
    void getEventByName() {
        EventInfo expected = new EventInfoFromReader(e1);
        EventInfo actual = eventGetter.getEventByName("mock1");
        assertEquals(expected, actual);
    }

    private class MockCalendarManager implements CalendarManager {

        @Override
        public long addEvent(String eventName, LocalDateTime startTime, LocalDateTime endTime, HashSet<String> tags, LocalDate date) {
            return 0;
        }

        @Override
        public void markEventAsCompleted(long eventId) {

        }

        @Override
        public List<EventReader> getAllEvents() {
            return allEvents;
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
            presentAllEventsSuccess = true;
        }

        public boolean isPresentAllEventsSuccess() {
            return presentAllEventsSuccess;
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
