package main.java.controller;

import main.java.entity.Task;

import java.time.LocalDateTime;

public interface TaskToEventAutoController {

    boolean suggestTimeToUser(Task task);
}
