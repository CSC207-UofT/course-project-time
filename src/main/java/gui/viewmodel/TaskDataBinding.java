package gui.viewmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TaskDataBinding {

    private long taskId = 0;

    private final PropertyChangeSupport observable;

    public TaskDataBinding() {
        this.taskId = 0;
        this.observable = new PropertyChangeSupport(this);
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        long oldId = this.taskId;
        this.taskId = taskId;
        observable.firePropertyChange("taskId", oldId, taskId);
    }

    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener("taskId", observer);
    }
}
