package gui.viewmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TaskDataBinding {

    private long taskId;
    private static long count = 0;  // has no actual meaning, just there to trigger property change

    private final PropertyChangeSupport observable;

    public TaskDataBinding() {
        this.taskId = 0;
        this.observable = new PropertyChangeSupport(this);
    }

    public void setTaskId(long taskId) {
        long oldId = this.taskId;
        this.taskId = taskId;
        count++;  // so that whenever this method is called, the observer updates
        observable.firePropertyChange("taskId", oldId, taskId);
        observable.firePropertyChange("count", count - 1, count);
    }

    public void addObserver(PropertyChangeListener observer) {
        observable.addPropertyChangeListener(observer);
    }
}
