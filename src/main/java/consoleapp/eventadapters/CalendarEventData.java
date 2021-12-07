package consoleapp.eventadapters;

import services.eventcreation.CalendarEventModel;
import services.strategybuilding.DatesForm;

import java.time.Duration;
import java.util.HashSet;

public class CalendarEventData implements CalendarEventModel {

    private final String eventName;
    private final Duration duration;
    private final DatesForm form;
    private final HashSet<String> tags;

    public CalendarEventData(String eventName, Duration duration, DatesForm form, HashSet<String> tags) {
        this.eventName = eventName;
        this.duration = duration;
        this.form = form;
        this.tags = tags;
    }

    @Override
    public String getName() {
        return eventName;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public DatesForm getForm() {
        return form;
    }

    @Override
    public HashSet<String> getTags() {
        return tags;
    }

}
