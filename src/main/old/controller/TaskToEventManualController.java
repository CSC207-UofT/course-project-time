package main.old.controller;

import main.old.entity.Task;

import java.time.LocalDateTime;

public interface TaskToEventManualController {
    boolean checkUserSuggestedTime(Task task, LocalDateTime userSuggestedTime);
}

