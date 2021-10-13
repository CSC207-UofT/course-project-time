package main.java;

import java.util.ArrayList;
import java.util.List;

public class TodoList{
    private List<Task> completedlist = new ArrayList<>();
    private List<Task> incompletedlist = new ArrayList<>();


    /**
     * Check if task is completed, if it is completed then remove it from
     * incompletedlist and add to completedlist
     * @param task the task that will be removed from incompletedlist and added to completedlist if task is completed
     */
    private void notCompleted(Task task){
        if(task.getCompleted()){
            incompletedlist.remove(task);
            completedlist.add(task);
        }
    }

    /**
     * Check for all tasks in uncompletedList,
     * move the completed tasks into completedList
     */
    public void checkCompletionAll() {
        for (Task task : incompletedlist) {
            notCompleted(task);
        }
    }

    public void addTask(Task task){
        if(task.getCompleted()){
            completedlist.add(task);
        }
        else{
            incompletedlist.add(task);
        }
    }

}
