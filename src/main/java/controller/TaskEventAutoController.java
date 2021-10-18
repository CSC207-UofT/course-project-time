package main.java.controller;

import java.time.LocalDateTime;

public interface TaskEventAutoController {
    boolean confirmTimeWithUser(LocalDateTime time);
}
