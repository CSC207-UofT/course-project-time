package gui.viewmodel.todolist;

import consoleapp.taskadapters.NewTodoListTaskData;
import gui.viewmodel.ViewModel;
import services.taskcreation.TodoListTaskCreationBoundary;
import services.taskcreation.TodoListTaskCreationModel;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AddTaskPageViewModel extends ViewModel {

    private final TodoListTaskCreationBoundary taskAdder;

    public AddTaskPageViewModel(TodoListTaskCreationBoundary taskAdder) {
        this.taskAdder = taskAdder;
    }

    /**
     * Adds a task.
     * @param taskName name of the task
     * @param dueDate date of the deadline
     * @param dueTimeHours hour of the deadline
     * @param dueTimeMinutes minute of the deadline
     * @param duration duration needed for the task in minutes
     * @param subtasks list of subtasks
     */
    public void addTask(String taskName, LocalDate dueDate, String dueTimeHours, String dueTimeMinutes, String duration,
                        List<String> subtasks) {
        Duration timeNeeded = Duration.ofMinutes(Long.parseLong(duration));
        LocalTime dueTime = LocalTime.of(Integer.parseInt(dueTimeHours), Integer.parseInt(dueTimeMinutes));
        LocalDateTime deadline = LocalDateTime.of(dueDate, dueTime);
        TodoListTaskCreationModel newTask = new NewTodoListTaskData(taskName, timeNeeded, deadline, subtasks);
        taskAdder.addTask(newTask);
    }

    /**
     * Adds a task with no deadline.
     * @param taskName name of the task
     * @param duration duration needed for the task
     * @param subtasks list of subtasks
     */
    public void addTask(String taskName, String duration, List<String> subtasks) {
        Duration timeNeeded = Duration.ofMinutes(Long.parseLong(duration));
        TodoListTaskCreationModel newTask = new NewTodoListTaskData(taskName, timeNeeded, null, subtasks);
        taskAdder.addTask(newTask);
    }

}
