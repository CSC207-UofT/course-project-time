package services.eventcreation;

import services.strategybuilding.DatesForm;

import java.util.Set;

public class EventFromTaskData implements EventFromTaskModel {

    private final Set<String> tags;
    private final DatesForm form;
    private final long taskId;

    public EventFromTaskData(Set<String> tags, DatesForm form, long taskId) {
        this.tags = tags;
        this.form = form;
        this.taskId = taskId;
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }

    @Override
    public DatesForm getForm() {
        return form;
    }

    @Override
    public long getTaskId() {
        return taskId;
    }
}
