package main.java;

import java.util.ArrayList;
import java.util.List;


public class TodoList{
    private List<Task> completedList = new ArrayList<>();
    private List<Task> uncompletedList = new ArrayList<>();

    /**
     * Check if task is completed, if it is completed then remove it from
     * uncompletedList and add to completedList
     * @param task the task that will be removed from uncompletedList and added to completedList if task is completed
     */
    private void completedTask(Task task){
        if(task.getCompleted()){
            uncompletedList.remove(task);
            completedList.add(task);
        }
    }

    /**
     * Check for all tasks in uncompletedList,
     * move the completed tasks into completedList
     */
    public void checkCompletionAll() {
        for (Task task : uncompletedList) {
            completedTask(task);
        }
    }
}
