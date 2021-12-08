package services.eventcreation;

import services.notification.NotificationAdder;
import services.notification.NotificationCreationModel;
import services.notification.NotificationData;
import services.notification.NotificationFormatter;

public class EventAdderWithNotification implements CalendarEventCreationBoundary {

    private final CalendarEventCreationBoundary service;
    private final NotificationAdder notificationAdder;
    private final NotificationFormatter notificationFormatter;

    public EventAdderWithNotification(CalendarEventCreationBoundary service, NotificationAdder notificationAdder, NotificationFormatter notificationFormatter) {
        this.service = service;
        this.notificationAdder = notificationAdder;
        this.notificationFormatter = notificationFormatter;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {
        long eventId = service.addEvent(eventData);
        String eventName = eventData.getName();

        // TODO: get date, startTime, endTime
        String message = notificationFormatter.formatEventNotificationMessage(
                eventName,
                null,
                null,
                null);

        // TODO: get notificationTimeInAdvance, notificationDateTime
        NotificationCreationModel notificationData = new NotificationData(
                eventId,
                null,
                null,
                message);

        notificationAdder.addNotification(notificationData);
        return eventId;
    }
}
