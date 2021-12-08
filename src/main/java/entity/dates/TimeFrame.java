package entity.dates;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeFrame {

    public final LocalDateTime startTime;
    public final Duration duration;

    public TimeFrame(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.duration = Duration.between(startTime, endTime);
    }

    public TimeFrame(LocalDateTime startTime, Duration duration) {
        this.startTime = startTime;
        this.duration = duration;
    }
}
