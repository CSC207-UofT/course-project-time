package services.eventcreation;

import services.notification.NotificationAdder;
import services.notification.NotificationCreationModel;
import services.notification.NotificationData;
import services.notification.NotificationFormat;

public class EventAdderWithNotification implements CalendarEventCreationBoundary {

    private final CalendarEventCreationBoundary service;
    private final NotificationAdder notificationAdder;
    private final NotificationFormat notificationFormat;

    public EventAdderWithNotification(CalendarEventCreationBoundary service, NotificationAdder notificationAdder, NotificationFormat notificationFormat) {
        this.service = service;
        this.notificationAdder = notificationAdder;
        this.notificationFormat = notificationFormat;
    }

    @Override
    public long addEvent(CalendarEventModel eventData) {
        long eventId = service.addEvent(eventData);
        String eventName = eventData.getName();

        // TODO: get date, startTime, endTime
        String message = notificationFormat.formatEventNotificationMessage(
                eventName,
                null,
                null,
                null);

        // TODO: get timeInAdvance, notificationTimeInAdvance, notificationDateTime
        NotificationCreationModel notificationData = new NotificationData(
                eventId,
                null,
                null,
                message);

        notificationAdder.addNotification(notificationData);
        return eventId;
    }
}
