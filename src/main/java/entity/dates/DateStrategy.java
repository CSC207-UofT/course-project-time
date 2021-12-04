package entity.dates;

import java.time.LocalDateTime;
import java.util.List;

public interface DateStrategy {

    List<LocalDateTime> datesBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
