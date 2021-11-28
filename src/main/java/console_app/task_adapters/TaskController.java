package console_app.task_adapters;

import console_app.NotificationController;
import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskGetter;
import services.task_presentation.TaskInfo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TaskController {
    private final TaskGetter taskGetter;
    private final TodoListTaskCreationBoundary taskAdder;
    private final TaskSaver taskSaver;
    private final NotificationController notificationController;

    public TaskController(TaskGetter taskGetter, TodoListTaskCreationBoundary taskAdder, TaskSaver taskSaver, NotificationController notificationController) {
        this.taskGetter = taskGetter;
        this.taskAdder = taskAdder;
        this.taskSaver = taskSaver;
        this.notificationController = notificationController;
    }

    /**
     * Displays all tasks.
     */
    public void presentAllTasks() {
        taskGetter.presentAllTasks();
    }

    public void createTask(String taskName, Duration timeNeeded,
                              LocalDateTime deadline, List<String> subTasks) {
        taskAdder.addTask(new NewTodoListTaskData(taskName, timeNeeded, deadline, subTasks));
    }

    public void completeTask(long taskId) {
        taskAdder.completeTask(taskId);
    }

    /**
     * Gets a Task by its id
     * @param id id of Task
     * @return TaskInfo with given id
     */
    public TaskInfo getTaskById(Long id) {
        return taskGetter.getTaskById(id);
    }

    /**
     * Displays task information in a numbered list for user to select a task
     * for further actions.
     * @return a mapping of task's position in the presented list and id
     */
    public Map<Integer, Long> presentAllTasksForUserSelection() {
        return taskGetter.presentAllTasksForUserSelection();
    }

    public void saveTodoList(String filename) throws IOException {
        taskSaver.save(filename);
    }
}
