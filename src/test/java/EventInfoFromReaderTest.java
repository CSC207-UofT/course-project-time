import datagateway.event.EventReader;
import entity.dates.TimeFrame;
import services.eventcreation.EventInfoFromReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    void getTags() {
        Set<String> expected = new HashSet<>();
        expected.add("CSC207");

        Set<String> actual = eventInfoFromReader.getTags();
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
        public Duration getDuration() {
            return null;
        }
        @Override
        public Set<String> getTags() {
            return tags;
        }

        @Override
        public Set<TimeFrame> getDatesBetween(LocalDateTime startTime, LocalDateTime endTime) {
            return null;
        }

        @Override
        public String getWhen() {
            return null;
        }
        @Override
        public boolean getCompleted() {
            return false;
        }
    }
}
