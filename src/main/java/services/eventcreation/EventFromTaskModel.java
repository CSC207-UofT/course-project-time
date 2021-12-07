package services.eventcreation;

import services.strategybuilding.DatesForm;

import java.util.Set;

public interface EventFromTaskModel {

    Set<String> getTags();

    DatesForm getForm();

    long getTaskId();
}
