package main.java.console_app.task_adapters;

import main.java.console_app.ApplicationDriver;
import main.java.services.task_presentation.TaskInfo;
import main.java.services.task_presentation.TodoListPresenter;
import main.java.services.task_presentation.TodoListsInfo;

import java.util.ArrayList;
import java.util.List;

public class ConsoleTaskPresenter implements TodoListPresenter {
    private final ApplicationDriver applicationDriver;

    public ConsoleTaskPresenter(ApplicationDriver applicationDriver) {
        this.applicationDriver = applicationDriver;
    }

    @Override
    public void presentTasks(TodoListsInfo todoListInfo) {
        List<String> taskFormattedInfo = new ArrayList<>();
        List<TaskInfo> tasks = todoListInfo.getAllTasks();
        for (TaskInfo ti : tasks) {

            String name = ti.getName();

            String deadline;
            if (ti.getDeadline() != null) {
                deadline = ti.getDeadline().toString();
            } else {
                deadline = "NO DEADLINE";
            }

            String subtasks = ti.getSubtasks().toString();
            boolean completed = ti.getCompleted();

            String output = "Task: " + name + ", "
                    + "deadline = " + deadline + ", "
                    + "subtasks = " + subtasks + ", "
                    + "completed = " + completed;
            taskFormattedInfo.add(output);
        }
        this.applicationDriver.printTasks(taskFormattedInfo);
    }
}
