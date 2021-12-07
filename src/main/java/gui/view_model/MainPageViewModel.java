package gui.view_model;


import data_gateway.event.EventReader;
import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskRepository;
import data_gateway.task.TaskReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;


public class MainPageViewModel extends ViewModel{
    private final ObservableTaskRepository taskRepository;
    private final ObservableEventRepository eventRepository;
    private final ObservableMap<String, String> taskInfoMap;
    private final ObservableMap<String, String> eventInfoMap;

    public MainPageViewModel(ObservableTaskRepository taskRepository, ObservableEventRepository eventRepository){
        this.taskRepository = taskRepository;
        this.eventRepository = eventRepository;

        taskRepository.addCreationObserver(this::handleCreation);
        taskRepository.addUpdateObserver(this::handleUpdate);

        List<TaskReader> tasks = taskRepository.getAllTasks().get(0L);
        List<EventReader> events = eventRepository.getAllEvents();
        this.taskInfoMap = generateTaskMap(tasks);
        this.eventInfoMap = generateEventMap(events);
    }

    private ObservableMap<String, String> generateTaskMap(List<TaskReader> taskReader) {
        ObservableMap<String, String> taskMap = FXCollections.observableHashMap();
        if (taskReader.isEmpty()){
            taskMap.put("No Scheduled Tasks", null);
        }
        for (TaskReader task: taskReader) {
            String taskName = task.getName();

            String deadline = new String("No Deadline");
            if (task.getDeadline() != null) {
                deadline = task.getDeadline().format(
                        DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.MEDIUM,
                                FormatStyle.SHORT));
            }

            taskMap.put(taskName, deadline);
        }
        return taskMap;
    }

    private ObservableMap<String, String> generateEventMap(List<EventReader> eventReader) {
        ObservableMap<String, String> eventMap = FXCollections.observableHashMap();
        if (eventReader.isEmpty()){
            eventMap.put("No Scheduled Events", null);
        }

        for (EventReader event: eventReader) {
            String eventName = event.getName();

            String startTime = event.getStartTime().format(
                    DateTimeFormatter.ofLocalizedDateTime(
                            FormatStyle.MEDIUM,
                            FormatStyle.SHORT));

            eventMap.put(eventName, startTime);
        }
        return eventMap;
    }

    public ObservableMap<String, String> getTaskInfoMap() {
        return this.taskInfoMap;
    }

    public ObservableMap<String, String> getEventInfoMap() {
        return this.eventInfoMap;
    }

    public void handleCreation(TaskReader taskReader) {

    }

    public void handleUpdate(TaskReader taskReader) {

    }
}
