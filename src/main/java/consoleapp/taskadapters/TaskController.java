package consoleapp.taskadapters;

import services.taskcreation.TaskSaver;
import services.taskcreation.TodoListTaskCreationBoundary;
import services.taskpresentation.TaskInfo;
import services.updateentities.UpdateTaskBoundary;
import services.taskpresentation.TodoListDisplayBoundary;
import services.taskpresentation.TodoListRequestBoundary;


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
    private final UpdateTaskBoundary taskUpdater;

    public TaskController(TodoListRequestBoundary taskGetter, TodoListDisplayBoundary taskOutputter,
                          TodoListTaskCreationBoundary taskAdder, TaskSaver taskSaver, UpdateTaskBoundary taskUpdater) {

        this.taskGetter = taskGetter;
        this.taskOutputter = taskOutputter;
        this.taskAdder = taskAdder;
        this.taskSaver = taskSaver;
        this.taskUpdater = taskUpdater;
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

    public void updateName(long taskId, String newName){
        taskUpdater.updateName(taskId, newName);
    }

    public void updateDuration(long id, Duration newDuration){
        taskUpdater.updateDuration(id, newDuration);
    }

    public void updateDeadline(long id, LocalDateTime newDeadline){
        taskUpdater.updateDeadline(id, newDeadline);
    }

    public void addSubtask(long id, String subtask){
        taskUpdater.addSubtask(id, subtask);
    }

    public void removeSubtask(long id, String subtask){
        taskUpdater.removeSubtask(id, subtask);
    }

    public void completeTask(long taskId) {
        taskUpdater.completeTask(taskId);
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
