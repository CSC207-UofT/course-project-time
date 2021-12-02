package console_app.task_adapters;

import services.task_creation.TaskSaver;
import services.task_creation.TodoListTaskCreationBoundary;
import services.task_presentation.TaskInfo;
import services.task_presentation.TodoListDisplayBoundary;
import services.task_presentation.TodoListRequestBoundary;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TaskController {
    private final TodoListRequestBoundary taskGetter;
    private final TodoListDisplayBoundary taskOutputter;
    private final TodoListTaskCreationBoundary taskAdder;
    private final TaskSaver taskSaver;

    public TaskController(TodoListRequestBoundary taskGetter, TodoListDisplayBoundary taskOutputter,
                          TodoListTaskCreationBoundary taskAdder, TaskSaver taskSaver) {
        this.taskGetter = taskGetter;
        this.taskOutputter = taskOutputter;
        this.taskAdder = taskAdder;
        this.taskSaver = taskSaver;
    }

    /**
     * Displays all tasks.
     */
    public void presentAllTasks() {
        taskOutputter.presentAllTasks();
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
        return taskOutputter.presentAllTasksForUserSelection();
    }

    public void saveTodoList(String filename) throws IOException {
        taskSaver.save(filename);
    }
}
