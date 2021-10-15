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

    private void notCompleted(Task task){
        if(task.getCompleted()){
            uncompletedList.remove(task);
            completedList.add(task);
        }
    }

    /**
     * Check for all tasks in uncompletedList,
     * move the completed tasks into completedList
     */
    public void updateCompletedList() {
        for (Task task : uncompletedList) {
            notCompleted(task);
        }
    }

    public List<Task> getTasks()
    {
        return uncompletedList;
    }


    public void addTask(Task task){
        if(task.getCompleted()){
            completedList.add(task);
        }
        else{
            uncompletedList.add(task);
        }
    }

    public List<Task> getUncompletedList(){
        return this.uncompletedList;
    }

    public List<Task> getCompletedList() { return this.completedList; }

}
