package main.java.controller;

import main.java.entity.Task;

import java.time.LocalDateTime;

public interface TaskToEventManualController {
    boolean checkUserSuggestedTime(Task task, LocalDateTime userSuggestedTime);
}

