package datagateway;

import datagateway.event.CalendarManager;
import datagateway.event.EventReader;
import datagateway.event.ObservableEventRepository;
import org.joda.time.DateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


// ICS exporter based on documentation by https://icalendar.org/
public class ICSExporter implements ICSGateway {


    private final TimeZone local = TimeZone.getDefault();

    public void saveICS(CalendarManager cal) {
        List<EventReader> events = cal.getAllEvents();

        FileWriter writer;
        try {
            writer = new FileWriter("time_calendar_export.ics", false);
            writer.write(generateHeader());
            writer.write(generateTimeZone());
            writer.append(generateICSEvents(events));
            writer.append("END:VCALENDAR");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String generateHeader() {
        return  "BEGIN:VCALENDAR\n" +
                "PRODID:-//Time//Time Calendar 70.9054//EN\n" +
                "VERSION:2.0\n" +
                "CALSCALE:GREGORIAN\n" +
                "METHOD:PUBLISH\n" +
                "X-WR-CALNAME:\n" +
                "X-WR-TIMEZONE:" + TimeZone.getDefault().getID() +"\n" +
                "X-WR-CALDESC: Autogenerated Calendar from Time Management System\n";
    }

    String generateTimeZone() {

        return "BEGIN:VTIMEZONE\n" +
                "TZID:" + local.getID() + "\n" +
                "X-LIC-LOCATION:" + local.getDisplayName() + "\n" +
                "BEGIN:STANDARD\n" +
                "TZOFFSETFROM:" + formatOffset(-5) +"\n" +
                "TZOFFSETTO:" + formatOffset(-5 - 1) + "\n" +
                "TZNAME:" + local.getDisplayName(true, 0) + "\n" +
                "DTSTART:19701025T020000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=10;BYDAY=-1SU\n" +
                "END:STANDARD\n" +
                "BEGIN:DAYLIGHT\n" +
                "TZOFFSETFROM:" + formatOffset(-5 - 1) + "\n" +
                "TZOFFSETTO:" + formatOffset(-5) +"\n" +
                "TZNAME:" + local.getDisplayName() + "\n" +
                "DTSTART:19700329T010000\n" +
                "RRULE:FREQ=YEARLY;BYMONTH=3;BYDAY=-1SU\n" +
                "END:DAYLIGHT\n" +
                "END:VTIMEZONE\n";
    }

    String formatOffset(int offset) {
        String formatted = "";
        if(offset >= 0) {
            formatted += "+";
        } else {
            formatted += "-";
        }

        String stringOffset = String.valueOf(offset);
        if(stringOffset.length() == 1) {
            formatted += "0" + stringOffset;
        } else {
            formatted += stringOffset;
        }

        return formatted + "00";
    }

    /**
     * For a list of events as ics strings
     * @param events the events to be formated
     * @return a string containing a sequence of ics file events
     */
    String generateICSEvents(List<EventReader> events) {

        StringBuilder eventsString = new StringBuilder();



        for(EventReader event : events) {
            LocalDate date = (LocalDate)event.getDates().toArray()[0];
            LocalDateTime start = date.atTime(event.getStartTime());
            LocalDateTime end = date.atTime(event.getEndTime());

            eventsString.append("BEGIN:VEVENT\n");
            eventsString.append("UID:").append(event.getId()).append("\n");
            eventsString.append("DTSTART:").append(formatDate(start)).append("\n");
            eventsString.append("DTEND:").append(formatDate(end)).append("\n");
            eventsString.append("DTSTAMP:").append(formatDate(LocalDateTime.now()));
            eventsString.append("DESCRIPTION:").append(formatTags(event.getTags())).append("\n");
            eventsString.append("END:VEVENT\n");

        }

        return eventsString.toString();
    }

    String formatDate(LocalDateTime dateTime) {

        return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
    }

    String formatTags(Set<String> tags) {

        StringBuilder stringTags = new StringBuilder();

        for(String tag : tags) {
            stringTags.append(tag).append(" ");
        }
        return stringTags.toString();
    }
}