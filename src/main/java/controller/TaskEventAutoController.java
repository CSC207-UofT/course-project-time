package main.java.controller;

import main.java.entity.Task;

import java.time.LocalDateTime;

public interface TaskEventAutoController {

    boolean suggestTimeToUser(Task task);
}
