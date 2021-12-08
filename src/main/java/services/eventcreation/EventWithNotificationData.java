package services.eventcreation;

import services.strategybuilding.DatesForm;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class EventWithNotificationData implements EventWithNotificationModel{

    private final String eventName;
    private final Duration duration;
    private final DatesForm datesForm;
    private final Set<String> tags;
    private final LocalDate eventDate;
    private final LocalTime eventStartTime;
    private final LocalTime eventEndTime;
    private final Duration notificationTimeInAdvance;

    EventWithNotificationData(CalendarEventModel eventData,
                              LocalDate eventDate,
                              LocalTime eventStartTime,
                              LocalTime eventEndTime,
                              Duration notificationTimeInAdvance) {
        this.eventName = eventData.getName();
        this.duration = eventData.getDuration();
        this.datesForm = eventData.getForm();
        this.tags = eventData.getTags();
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.notificationTimeInAdvance = notificationTimeInAdvance;
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
        return datesForm;
    }

    @Override
    public Set<String> getTags() {
        return tags;
    }

    @Override
    public LocalDate getEventDate() {
        return eventDate;
    }

    @Override
    public LocalTime getEventStartTime() {
        return eventStartTime;
    }

    @Override
    public LocalTime getEventEndTime() {
        return eventEndTime;
    }

    @Override
    public Duration getNotificationTimeInAdvance() {
        return notificationTimeInAdvance;
    }
}
