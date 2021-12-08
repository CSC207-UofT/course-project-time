package gui.viewmodel;

import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.Entry;
import datagateway.event.EventReader;
import gui.utility.EventHelper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import services.eventcreation.CalendarEventCreationBoundary;
import services.eventcreation.EventSaver;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.eventpresentation.EventInfo;
import services.updateentities.UpdateEventBoundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarViewModel extends ViewModel {

    private final CalendarEventCreationBoundary eventAdder;
    private final UpdateEventBoundary eventUpdater;
    private final EventSaver eventSaver;

    private final ObservableList<Entry<String>> entryList;
    private final Map<String, Long> entryToEventIdMapping;

    // booleans to indicate if the event given by the repositories
    // was originated from the current view model.
    private boolean eventUpdatedFromView;
    private boolean eventCreatedFromView;

    public CalendarViewModel(CalendarEventCreationBoundary eventAdder,
                             CalendarEventRequestBoundary eventGetter,
                             UpdateEventBoundary eventUpdater,
                             EventSaver eventSaver) {
        this.eventAdder = eventAdder;
        this.eventUpdater = eventUpdater;
        this.eventSaver = eventSaver;

        this.entryList = FXCollections.observableArrayList(new ArrayList<>());
        this.entryToEventIdMapping = new HashMap<>();
        initializeIdMappingAndEntryList(eventGetter.getEvents());

        this.entryList.addListener((ListChangeListener<Entry<String>>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    Entry<String> entry = c.getAddedSubList().get(0);
                    onCreation(entry);
                } else if (c.wasRemoved()) {
                    Entry<String> entry = c.getRemoved().get(0);
                    onDeletion(entry);
                }
            }
        });
    }

    /**
     * Handles changes to the entryList when an entry is added.
     * If event was created from the view, pass it to be stored in the database.
     * Else, ignore it as the handleCreation method called by the Observable Repository will handle it.
     * @param entry the entry that has been added
     */
    private void onCreation(Entry<String> entry) {
        if (eventCreatedFromView) {
            eventAdder.addEvent(EventHelper.entryToCalendarEventModel(entry));
        }
    }

    private void onDeletion(Entry<String> entry) {

    }

    /**
     * Initializes the mapping between entries id from the view and event id from the program.
     * @param infoList the list of event info received from the program
     */
    private void initializeIdMappingAndEntryList(List<EventInfo> infoList) {
        for (EventInfo eventInfo : infoList) {
            Entry<String> entry = EventHelper.eventInfoToEntry(eventInfo);
            entryToEventIdMapping.put(entry.getId(), eventInfo.getId());
            entryList.add(entry);
        }
    }

    public ObservableList<Entry<String>> getEntryList() {
        return this.entryList;
    }

    /**
     * Called by the observable repository.
     * If the event creation was originated from the current view model, add the entry
     * to event mapping.
     * Else, create a new entry and add the entry to event mapping.
     * @param eventReader data of new event created
     */
    public void handleCreation(EventReader eventReader) {
        if (!eventCreatedFromView) {
            Entry<String> newEntry = EventHelper.eventReaderToEntry(eventReader);
            entryToEventIdMapping.put(newEntry.getId(), eventReader.getId());
        } else {
            // find the entry with no mapping and add mapping
            for (Entry<String> entry : entryList) {
                if (!entryToEventIdMapping.containsKey(entry.getId())) {
                    entryToEventIdMapping.put(entry.getId(), eventReader.getId());
                }
            }
            // set it to false when the process of event creation is over
            eventCreatedFromView = false;
        }
    }

    /**
     * Called by the observable repository.
     * If the event update was originated from the current view model, nothing is done.
     * Else, finds and updates the corresponding entry.
     * @param eventReader data of new event created
     */
    public void handleUpdate(EventReader eventReader) {
        if (!eventUpdatedFromView) {
            this.updateEntry(eventReader);
        } else {
            eventUpdatedFromView = false;
        }
    }

    /**
     * Called by the view when an event is updated. Propagates update to the repository
     * @param event the (javafx) event that triggered this call
     */
    public void updateEventFromView(CalendarEvent event) {
        eventUpdatedFromView = true;

        Entry<?> updatedEntry = event.getEntry();
        long correspondingEventId = entryToEventIdMapping.get(updatedEntry.getId());
        if (event.getEventType() == CalendarEvent.ENTRY_TITLE_CHANGED) {
            eventUpdater.updateName(correspondingEventId, updatedEntry.getTitle());
        }
        if (event.getEventType() == CalendarEvent.ENTRY_INTERVAL_CHANGED) {
            eventUpdater.updateStartTime(correspondingEventId, updatedEntry.getStartTime());

            // set eventUpdatedFromView to be true again as the previous update call makes this false
            eventUpdatedFromView = true;
            eventUpdater.updateEndTime(correspondingEventId, updatedEntry.getEndTime());
        }
    }

    /**
     * Handles event creation from view
     * @param newEntry the new entry creation in the view
     */
    public void addEventFromView(Entry<String> newEntry) {
        eventCreatedFromView = true;
        entryList.add(newEntry);
    }

    /**
     * Persists data into the database
     */
    public void saveData() {
        try {
            eventSaver.saveEventData("EventData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds the corresponding entry to the eventReader and updates the information of the entry.
     * The entry is guaranteed to exist in entryToEventIdMapping and entryList.
     * @param eventReader the reader that holds relevant information that corresponds to an entry
     */
    private void updateEntry(EventReader eventReader) {
        if (entryToEventIdMapping.containsValue(eventReader.getId())) {
            Entry<String> entry = this.getEntryFromEvent(eventReader);
            assert entry != null;
            this.updateEntryWithEventReader(entry, eventReader);
        } else {
            System.err.println("Event data may not be synchronized");
        }
    }

    /**
     * Find an entry that corresponds to the eventReader
     * @param eventReader the reader that holds relevant information that corresponds to an entry
     * @return an entry
     */
    private Entry<String> getEntryFromEvent(EventReader eventReader) {
        for (String entryId : entryToEventIdMapping.keySet()) {
            if (eventReader.getId() == entryToEventIdMapping.get(entryId)) {
                for (Entry<String> entry : entryList) {
                    if (entryId.equals(entry.getId())) {
                        return entry;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Given an entry and a reader, updates the entry such that
     * it matches information in the reader
     * @param entry the entry to be updated
     * @param eventReader the reader with the information
     */
    private void updateEntryWithEventReader(Entry<String> entry, EventReader eventReader) {
        if (!entry.getTitle().equals(eventReader.getName())) {
            entry.setTitle(eventReader.getName());
        }
        if (!entry.getStartDate().equals(eventReader.getDates().iterator().next())) {
            entry.changeStartDate(eventReader.getDates().iterator().next());
        }
        if (!entry.getEndDate().equals(eventReader.getDates().iterator().next())) {
            entry.changeEndDate(eventReader.getDates().iterator().next());
        }
        if (!entry.getStartAsLocalDateTime().toLocalTime().equals(eventReader.getStartTime())) {
            entry.changeStartTime(eventReader.getStartTime());
        }
        if (!entry.getEndAsLocalDateTime().toLocalTime().equals(eventReader.getEndTime())) {
            entry.changeStartTime(eventReader.getEndTime());
        }
    }
}
