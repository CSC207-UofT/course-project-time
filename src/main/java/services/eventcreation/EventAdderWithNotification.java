package services.eventcreation;

import services.notification.NotificationAdder;
import services.notification.NotificationCreationModel;
import services.notification.NotificationData;
import services.notification.NotificationFormatter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventAdderWithNotification implements EventWithNotificationCreationBoundary {

    private final CalendarEventCreationBoundary service;
    private final NotificationAdder notificationAdder;
    private final NotificationFormatter notificationFormatter;

    public EventAdderWithNotification(CalendarEventCreationBoundary service, NotificationAdder notificationAdder, NotificationFormatter notificationFormatter) {
        this.service = service;
        this.notificationAdder = notificationAdder;
        this.notificationFormatter = notificationFormatter;
    }

    public long addEvent(EventWithNotificationModel eventData) {
        long eventId = service.addEvent(eventData);
        String eventName = eventData.getName();
        LocalDate eventDate = eventData.getEventDate();
        LocalTime eventStartTime = eventData.getEventStartTime();
        LocalTime eventEndTime = eventData.getEventEndTime();

        String message = notificationFormatter.formatEventNotificationMessage(
                eventName,
                eventDate,
                eventStartTime,
                eventEndTime);

        Duration notificationTimeInAdvance = eventData.getNotificationTimeInAdvance();
        LocalDateTime eventStartDateTime = LocalDateTime.of(eventDate, eventStartTime);
        LocalDateTime notificationDateTime = eventStartDateTime.minus(notificationTimeInAdvance);
        NotificationCreationModel notificationData = new NotificationData(
                eventId,
                notificationTimeInAdvance,
                notificationDateTime,
                message);

        notificationAdder.addNotification(notificationData);
        return eventId;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {
        return service.addEvent(eventData);
    }

    @Override
    public long addEvent(EventFromTaskModel eventData) {
        return service.addEvent(eventData);
    }
}
