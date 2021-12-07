package services.eventcreation;

import services.strategybuilding.DatesForm;

import java.time.Duration;
import java.util.HashSet;

public interface CalendarEventModel {
    String getName();
    Duration getDuration();
    DatesForm getForm();
    HashSet<String> getTags();

}
