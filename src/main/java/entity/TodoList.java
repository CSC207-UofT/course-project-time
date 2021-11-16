package entity;

import java.util.ArrayList;
import java.util.List;

public class TodoList{
    private final List<Task> completedList = new ArrayList<>();
    private final List<Task> uncompletedList = new ArrayList<>();


    /**
     * Check if task is completed, if it is completed then remove it from
     * uncompletedList and add to completedList
     * @param task the task that will be removed from uncompletedList and added to completedList if task is completed
     */

    private void notCompleted(Task task){
        if(task.getCompleted()){
            uncompletedList.remove(task);
            completedList.add(task);
        }
    }



    public void addTask(Task task){
        if(task.getCompleted()){
            completedList.add(task);
        }
        else{
            uncompletedList.add(task);
        }
    }

    public boolean isTaskinList(Task task){
        return completedList.contains(task) || uncompletedList.contains(task);
    }

}
