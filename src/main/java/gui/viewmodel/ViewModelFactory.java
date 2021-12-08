package gui.viewmodel;


import services.servicesfactory.ObservableRepositoryFactory;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;
import services.servicesfactory.ServicesFactory;

public class ViewModelFactory {

    private final ServicesFactory servicesFactory;
    private final ObservableEventRepository eventRepository;
    private final ObservableTaskRepository taskRepository;

    private CalendarViewModel calendarViewModel;
    private TodoListPageViewModel todoListPageViewModel;
    private AddTaskPageViewModel addTaskPageViewModel;

    public ViewModelFactory(ObservableRepositoryFactory repositoryFactory, ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
        eventRepository = repositoryFactory.makeEventRepository();
        taskRepository = repositoryFactory.makeTaskRepository();
    }

    public CalendarViewModel getCalendarViewModel() {
        if (calendarViewModel == null) {
            calendarViewModel = new CalendarViewModel(servicesFactory.makeEventCreator(),
                    servicesFactory.makeEventGetter(), servicesFactory.makeEventUpdater(), servicesFactory.makeEventSaver());
            eventRepository.addCreationObserver(calendarViewModel::handleCreation);
            eventRepository.addUpdateObserver(calendarViewModel::handleUpdate);
        }
        return calendarViewModel;
    }

    public TodoListPageViewModel getTodoListPageViewModel() {
        if (todoListPageViewModel == null) {
            todoListPageViewModel = new TodoListPageViewModel(servicesFactory.makeTaskGetter());
            taskRepository.addCreationObserver(todoListPageViewModel::handleCreation);
            taskRepository.addUpdateObserver(todoListPageViewModel::handleUpdate);
        }
        return todoListPageViewModel;
    }

    public AddTaskPageViewModel getAddTaskPageViewModel() {
        if (addTaskPageViewModel == null) {
            addTaskPageViewModel = new AddTaskPageViewModel(servicesFactory.makeTaskCreator());
        }
        return addTaskPageViewModel;
    }
}
