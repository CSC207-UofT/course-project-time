package main.java.use_case;

import java.time.LocalDateTime;

public interface TaskEventAutoController {
    boolean confirmTimeWithUser(LocalDateTime time);
}
