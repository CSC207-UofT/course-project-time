package gui.viewmodel;


import datagateway.event.EventReader;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;
import datagateway.task.TaskReader;
import services.eventcreation.EventInfoFromReader;
import services.eventcreation.EventSaver;
import services.eventpresentation.CalendarEventRequestBoundary;
import services.eventpresentation.EventInfo;
import services.taskcreation.TaskSaver;
import services.taskpresentation.TaskInfo;
import services.taskpresentation.TaskInfoFromTaskReader;
import services.taskpresentation.TodoListRequestBoundary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;


public class MainPageViewModel extends ViewModel implements Runnable{
    private final TodoListRequestBoundary taskGetter;
    private final TaskSaver taskSaver;
    private final CalendarEventRequestBoundary eventRepository;
    private final EventSaver eventSaver;

    private final List<TaskInfo> relevantTasks = new ArrayList<>();
    private final List<EventInfo> relevantEvents = new ArrayList<>();
    private final List<Consumer<Infos>> observers = new ArrayList<>();

    public MainPageViewModel(TodoListRequestBoundary taskGetter, TaskSaver taskSaver, CalendarEventRequestBoundary eventGetter, EventSaver eventSaver){
        this.taskGetter = taskGetter;
        this.taskSaver = taskSaver;
        this.eventRepository = eventGetter;
        this.eventSaver = eventSaver;

        updateRelevantTasks();
        updateRelevantEvents();

        new Thread(this).start();
    }

    /**
     * Flushes out current relevant tasks with data live from the {@link ObservableTaskRepository}
     */
    private void updateRelevantTasks() {
        List<TaskInfo> tasks = taskGetter.getTasks();
        relevantTasks.clear();
        for (TaskInfo taskInfo : tasks) {
            boolean taskEndsToday =
                    taskInfo.getDeadline() != null
                    && taskInfo.getDeadline().toLocalDate().equals(LocalDate.now());
            if (taskEndsToday)
                relevantTasks.add(taskInfo);
        }
        notifyObservers();
    }

    /**
     * Flushes out current relevant events with data live from the {@link ObservableEventRepository}
     */
    private void updateRelevantEvents() {
        List<EventInfo> events = eventRepository.getEvents();
        relevantEvents.clear();
        for(EventInfo eventInfo: events){
            for(LocalDate date: eventInfo.getDates())
                if (date.equals(LocalDate.now())){
                    relevantEvents.add(eventInfo);
                }
        }
        notifyObservers();
    }

    public String formatDeadline(LocalDateTime localDateTime) {
        return localDateTime.toLocalTime().format(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
    }

    public void addObserver(Consumer<Infos> observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(o -> o.accept(new Infos(relevantEvents, relevantTasks)));
    }

    public List<TaskInfo> getRelevantTasks() {
        return new ArrayList<>(relevantTasks);
    }

    public List<EventInfo> getRelevantEvents() {
        return new ArrayList<>(relevantEvents);
    }

    /**
     * A method listening to the {@link ObservableTaskRepository} on task creation event
     *
     * Adds the newly created task if it's relevant
     * @param taskReader the newly created task
     */
    public void handleCreation(TaskReader taskReader) {
        TaskInfo taskInfo = new TaskInfoFromTaskReader(taskReader);
        if (taskInfo.getDeadline() != null && taskInfo.getDeadline().toLocalDate().equals(LocalDate.now())){
            relevantTasks.add(taskInfo);
            notifyObservers();
        }
    }

    /**
     * A method listening to the {@link ObservableTaskRepository} on task update event
     *
     * Removes the updated task if it's not longer relevant and replaces it otherwise
     * @param taskReader the task that was updated
     */
    public void handleUpdate(TaskReader taskReader) {
        LocalDate todayDate = LocalDate.now();
        TaskInfo taskInfo = new TaskInfoFromTaskReader(taskReader);
        relevantTasks.removeIf(ti -> ti.getName().equals(taskInfo.getName()));
        if (taskInfo.getDeadline().toLocalDate().equals(todayDate)){
            relevantTasks.add(taskInfo);
        }

        notifyObservers();
    }

    public void handleCreation(EventReader eventReader) {
        for (LocalDate date : eventReader.getDates()){
            if (date.equals(LocalDate.now())){
                notifyObservers();
            }
        }
    }

    public void handleUpdate(EventReader eventReader) {
        EventInfo eventInfo = new EventInfoFromReader(eventReader);
        LocalDate todayDate = LocalDate.now();
        relevantEvents.removeIf(ei -> ei.getName().equals(eventInfo.getName()));
        for (LocalDate date : eventReader.getDates()) {
            if (date.equals(todayDate)) {
                relevantEvents.add(eventInfo);
            }
            notifyObservers();
        }
    }



    /**
     * Background thread which refreshes the cached relevant infos when the day changes.
     */
    @Override
    public void run() {
        LocalDate todayDate = LocalDate.now();
        while(!Thread.interrupted()){
            if(LocalDate.now().equals(todayDate.plusDays(1))){
                todayDate = LocalDate.now();
                updateRelevantTasks();
                updateRelevantEvents();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Persists all data to the database.
     */
    public void saveData() {
        try {
            eventSaver.saveEventData("EventData.json");
            taskSaver.save("TaskData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Just a data class containing both families of infos.
     */
    public static class Infos {
        public final List<EventInfo> eventInfos;
        public final List<TaskInfo> taskInfos;
        public Infos(List<EventInfo> eventInfos, List<TaskInfo> taskInfos) {
            this.eventInfos = eventInfos;
            this.taskInfos = taskInfos;
        }
    }
}
