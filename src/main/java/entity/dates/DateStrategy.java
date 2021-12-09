package entity.dates;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface DateStrategy extends Serializable {

    List<TimeFrame> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration eventDuration);

}
