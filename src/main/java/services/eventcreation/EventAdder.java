package services.eventcreation;

import datagateway.event.CalendarManager;
import entity.dates.DateStrategy;
import services.strategybuilding.DatesForm;
import services.strategybuilding.StrategyBuilderDirector;

import java.time.Duration;
import java.util.Set;

public class EventAdder implements CalendarEventCreationBoundary {

    private final CalendarManager calendarManager;

    public EventAdder(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {

        String eventName = eventData.getName();
        Set<String> tags = eventData.getTags();
        DatesForm form = eventData.getForm();
        Duration eventDuration = eventData.getDuration();

        StrategyBuilderDirector director = new StrategyBuilderDirector();
        DateStrategy strategy = director.createStrategy(form);

        return calendarManager.addEvent(eventName, strategy, eventDuration, tags);
    }

    @Override
    public long addEvent(EventFromTaskModel eventData) {

        Set<String> tags = eventData.getTags();
        DatesForm form = eventData.getForm();
        long taskId = eventData.getTaskId();

        StrategyBuilderDirector director = new StrategyBuilderDirector();
        DateStrategy strategy = director.createStrategy(form);

        return calendarManager.addEvent(taskId, strategy, tags);
    }

}
