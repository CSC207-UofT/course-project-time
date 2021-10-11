package main.java;

import java.util.ArrayList;

/**
 * A Task stores the name of the task, whether it is completed,
 * and subtasks (optional)
 */
public class Task {

    private String taskName;
    private boolean completed;
    private ArrayList<Task> subTasks;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
        this.subTasks = new ArrayList<Task>();
    }

    public void renameTask(String newName) {
        this.taskName = newName;
    }

    public void complete() {
        this.completed = true;
    }

    public void undoComplete() {
        this.completed = false;
    }

    /**
     * Add a subtask to subTasks.
     *
     * @param subTask the task that will be added
     */
    public void addSubTask(Task subTask) {
        this.subTasks.add(subTask);
    }

    /**
     * Remove a subtask from subTasks.
     *
     * @param subTask the task that will be removed
     * @return true iff subTask is in subTask and is removed successfully
     *
     */
    public boolean removeSubTask(Task subTask) {
        if (this.subTasks.contains(subTask)) {
            this.subTasks.remove(subTask);
            return true;
        } else {
            return false;
        }
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public ArrayList<Task> getSubTasks() {
        return this.subTasks;
    }

}
