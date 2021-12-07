package services.eventcreation;

import datagateway.event.CalendarManager;
import entity.dates.DateStrategy;
import services.strategybuilding.DatesForm;
import services.strategybuilding.StrategyBuilderDirector;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class EventAdder implements CalendarEventCreationBoundary {

    private final CalendarManager calendarManager;

    public EventAdder(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {

        String eventName = eventData.getName();
        HashSet<String> tags = eventData.getTags();
        DatesForm form = eventData.getForm();
        Duration eventDuration = eventData.getDuration();

        StrategyBuilderDirector director = new StrategyBuilderDirector();
        DateStrategy strategy = director.createStrategy(form);
        List<LocalDateTime> times = strategy.datesBetween(LocalDateTime.now(), LocalDateTime.now().plusYears(1));
        LocalDateTime startTime = times.get(0);

        return calendarManager.addEvent(eventName, startTime, startTime.plus(eventDuration), tags, startTime.toLocalDate());
    }

}