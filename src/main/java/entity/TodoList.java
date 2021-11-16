package entity;

import java.util.ArrayList;
import java.util.List;

public class TodoList{
    private final List<Task> completedList = new ArrayList<>();
    private final List<Task> uncompletedList = new ArrayList<>();



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
