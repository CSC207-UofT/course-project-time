package services.event_from_task_creation;

public interface EventFromTaskCreatorBoundary {

    boolean createEventFromTask(EventFromTaskModel eventFromTaskModel);

}
