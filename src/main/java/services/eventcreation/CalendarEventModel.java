package services.eventcreation;

import services.strategybuilding.DatesForm;

import java.time.Duration;
import java.util.Set;

public interface CalendarEventModel {
    String getName();
    Duration getDuration();
    DatesForm getForm();
    Set<String> getTags();

}
