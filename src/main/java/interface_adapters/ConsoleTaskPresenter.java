package main.java.interface_adapters;

import main.java.use_case.TaskOutputDTO;
import main.java.use_case.TodoListPresenter;
import main.java.use_case.TodoListOutputDTO;

import java.util.List;

public class ConsoleTaskPresenter implements TodoListPresenter {

    @Override
    public void presentTasks(TodoListOutputDTO todoListInfo) {
        List<TaskOutputDTO> tasks = todoListInfo.getAllTasks();
        if (tasks.size() == 0) {
            System.out.println("No tasks have been created");
        }
        for (TaskOutputDTO ti : tasks) {

            String name = ti.getName();
            String deadline = ti.getDeadline().toString();
            String subtasks = ti.getSubtasks().toString();
            boolean completed = ti.getCompleted();

            String output = "Task: " + name + ", "
                    + "deadline = " + deadline + ", "
                    + "subtasks = " + subtasks + ", "
                    + "completed = " + completed;
            System.out.println(output);
        }
    }
}
