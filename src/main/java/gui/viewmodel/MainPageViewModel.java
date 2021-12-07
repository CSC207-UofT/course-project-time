package gui.viewmodel;


import datagateway.event.EventReader;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;
import datagateway.task.TaskReader;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;


public class MainPageViewModel extends ViewModel implements Runnable{
    private final ObservableTaskRepository taskRepository;
    private final ObservableEventRepository eventRepository;
    private final List<TaskReader> relevantTasks = new ArrayList<>();
    private final List<EventReader> relevantEvents = new ArrayList<>();
    private final List<Consumer<Readers>> observers = new ArrayList<>();

    public MainPageViewModel(ObservableTaskRepository observableTaskRepository, ObservableEventRepository observableEventRepository){
        this.taskRepository = observableTaskRepository;
        this.eventRepository = observableEventRepository;

        observableTaskRepository.addCreationObserver(this::handleCreation);
        observableTaskRepository.addUpdateObserver(this::handleUpdate);

        observableEventRepository.addCreationObserver(this::handleCreation);
        observableEventRepository.addUpdateObserver(this::handleUpdate);

        updateRelevantTasks();
        updateRelevantEvents();

        new Thread(this).start();
    }

    /**
     * Flushes out current relevant tasks with data live from the {@link ObservableTaskRepository}
     */
    private void updateRelevantTasks() {
        List<TaskReader> tasks = taskRepository.getAllTasks().get(0L);
        relevantTasks.clear();
        for (TaskReader tr : tasks) {
            boolean taskEndsToday = tr.getDeadline() != null && tr.getDeadline().toLocalDate().equals(LocalDate.now());
            if (taskEndsToday)
                relevantTasks.add(tr);
        }
        notifyObservers();
    }

    /**
     * Flushes out current relevant events with data live from the {@link ObservableEventRepository}
     */
    private void updateRelevantEvents() {
        List<EventReader> events = eventRepository.getAllEvents();
        relevantEvents.clear();
        for(EventReader er: events){
            for(LocalDate date: er.getDates())
                if (date.equals(LocalDate.now())){
                    relevantEvents.add(er);
                }
        }
        notifyObservers();
    }

    public void addObserver(Consumer<Readers> observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(o -> o.accept(new Readers(relevantEvents, relevantTasks)));
    }

    public List<TaskReader> getRelevantTasks() {
        return new ArrayList<>(relevantTasks);
    }

    public List<EventReader> getRelevantEvents() {
        return new ArrayList<>(relevantEvents);
    }

    /**
     * A method listening to the {@link ObservableTaskRepository} on task creation event
     *
     * Adds the newly created task if it's relevant
     * @param taskReader the newly created task
     */
    public void handleCreation(TaskReader taskReader) {
        if (taskReader.getDeadline() != null && taskReader.getDeadline().toLocalDate().equals(LocalDate.now())){
            relevantTasks.add(taskReader);
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
        relevantTasks.removeIf(tr -> tr.getName().equals(taskReader.getName()));
        if (taskReader.getDeadline().toLocalDate().equals(todayDate)){
            relevantTasks.add(taskReader);
        }

        notifyObservers();
    }

    private void handleUpdate(EventReader eventReader) {
        LocalDate todayDate = LocalDate.now();
        relevantEvents.removeIf(er -> er.getName().equals(eventReader.getName()));
        for (LocalDate date : eventReader.getDates()) {
            if (date.equals(todayDate)) {
                relevantEvents.add(eventReader);
            }
            notifyObservers();
        }
    }

    private void handleCreation(EventReader eventReader) {
        for (LocalDate date : eventReader.getDates()){
            if (date.equals(LocalDate.now())){
                notifyObservers();
            }
        }
    }

    /**
     * Background thread which refreshes the cached relevant readers when the day changes.
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
     * Just a data class containing both families of readers.
     */
    public static class Readers {
        public final List<EventReader> eventReaders;
        public final List<TaskReader> taskReaders;
        public Readers(List<EventReader> eventReaders, List<TaskReader> taskReaders) {
            this.eventReaders = eventReaders;
            this.taskReaders = taskReaders;
        }
    }
}
