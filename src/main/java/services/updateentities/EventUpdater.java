package services.updateentities;


import datagateway.event.CalendarManager;
import services.strategybuilding.DatesForm;
import services.strategybuilding.StrategyBuilderDirector;

import java.time.Duration;

public class EventUpdater implements UpdateEventBoundary{
    final CalendarManager calendarManager;

    public EventUpdater(CalendarManager eventEntityManager){
        this.calendarManager = eventEntityManager;
    }


    @Override
    public void updateName(long id, String newName) {
        calendarManager.updateName(id, newName);
    }

    @Override
    public void updateDateStrategy(long id, DatesForm form) {
        StrategyBuilderDirector director = new StrategyBuilderDirector();
        calendarManager.updateDateStrategy(id, director.createStrategy(form));
    }

    @Override
    public void updateDuration(long id, Duration duration) {
        calendarManager.updateDuration(id, duration);
    }

    @Override
    public void addTag(long id, String tag) {
        calendarManager.addTag(id, tag);
    }

    @Override
    public void removeTag(long id, String tag) {
        calendarManager.removeTag(id, tag);
    }

    @Override
    public void markEventAsCompleted(long id) {
        calendarManager.markEventAsCompleted(id);
    }
}