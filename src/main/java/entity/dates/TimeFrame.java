package entity.dates;

import java.time.LocalDateTime;

public class TimeFrame {

    public final LocalDateTime startTime;
    public final LocalDateTime endTime;

    public TimeFrame(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
