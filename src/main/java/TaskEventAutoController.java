package main.java;

import java.time.LocalDateTime;

public interface TaskEventAutoController {
    boolean confirmTimeWithUser(LocalDateTime time);
}
