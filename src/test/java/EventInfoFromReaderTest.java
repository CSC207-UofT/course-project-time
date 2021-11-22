import data_gateway.EventReader;
import services.event_creation.EventInfoFromReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventInfoFromReaderTest {

    EventInfoFromReader eventInfoFromReader;
    MockEventReader reader;

    @BeforeEach
    void setup() {
        reader = new MockEventReader();
        eventInfoFromReader = new EventInfoFromReader(reader);
    }

    @Test
    void getName() {
        String expected = "MockName";
        String actual = eventInfoFromReader.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getStartTime() {
        LocalTime expected = LocalTime.of(12, 0);
        LocalTime actual = eventInfoFromReader.getStartTime();
        assertEquals(expected, actual);
    }

    @Test
    void getEndTime() {
        LocalTime expected = LocalTime.of(14, 0);
        LocalTime actual = eventInfoFromReader.getEndTime();
        assertEquals(expected, actual);
    }

    @Test
    void getTags() {
        Set<String> expected = new HashSet<>();
        expected.add("CSC207");

        Set<String> actual = eventInfoFromReader.getTags();
        assertEquals(expected, actual);
    }

    @Test
    void getDates() {
        Set<LocalDate> expected = new HashSet<>();
        expected.add(LocalDate.of(2021, 11, 15));

        Set<LocalDate> actual = eventInfoFromReader.getDates();
        assertEquals(expected, actual);
    }

    private static class MockEventReader implements EventReader {
        private final LocalTime startTime = LocalTime.of(12, 0);
        private final LocalTime endTime = LocalTime.of(14, 0);
        private final Set<String> tags = new HashSet<>();
        private final Set<LocalDate> dates = new HashSet<>();

        public MockEventReader() {
            tags.add("CSC207");
            dates.add(LocalDate.of(2021, 11, 15));
        }

        @Override
        public long getId() {
            return 0;
        }

        @Override
        public String getName() {
            return "MockName";
        }

        @Override
        public LocalTime getStartTime() {
            return startTime;
        }

        @Override
        public LocalTime getEndTime() {
            return endTime;
        }

        @Override
        public Set<String> getTags() {
            return tags;
        }

        @Override
        public Set<LocalDate> getDates() {
            return dates;
        }

        @Override
        public boolean getCompleted() {
            return false;
        }
    }
}
