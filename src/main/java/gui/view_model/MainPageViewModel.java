package gui.view_model;

import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskRepository;

public class MainPageViewModel extends ViewModel{
    private final ObservableTaskRepository taskRepository;
    private final ObservableEventRepository eventRepository;

    public MainPageViewModel(ObservableTaskRepository taskRepository, ObservableEventRepository eventRepository) {
        this.taskRepository = taskRepository;
        this.eventRepository = eventRepository;
    }
}
