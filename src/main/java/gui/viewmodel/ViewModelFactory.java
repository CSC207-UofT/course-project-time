package gui.viewmodel;


import services.servicesfactory.ObservableRepositoryFactory;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;
import services.servicesfactory.ServicesFactory;

public class ViewModelFactory {

    private final ServicesFactory servicesFactory;

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;
    private final TodoListPageViewModel todoListPageViewModel;
    private final AddTaskPageViewModel addTaskPageViewModel;

    public ViewModelFactory(ObservableRepositoryFactory repositoryFactory, ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
        ObservableEventRepository eventRepository = repositoryFactory.makeEventRepository();
        ObservableTaskRepository taskRepository = repositoryFactory.makeTaskRepository();

        this.monthlyCalendarViewModel = makeMonthlyCalendarViewModel();
        this.weeklyCalendarViewModel = makeWeeklyCalendarViewModel();
        this.todoListPageViewModel = new TodoListPageViewModel(taskRepository);
        this.addTaskPageViewModel = new AddTaskPageViewModel(taskRepository);
        addObserversToRepositories(eventRepository, taskRepository);
    }

    private void addObserversToRepositories(ObservableEventRepository eventRepository,
                                            ObservableTaskRepository taskRepository) {
//        eventRepository.addCreationObserver(monthlyCalendarViewModel);
//        eventRepository.addUpdateObserver(monthlyCalendarViewModel);
//        eventRepository.addCreationObserver(weeklyCalendarViewModel);
//        eventRepository.addUpdateObserver(weeklyCalendarViewModel);
//
//        taskRepository.addCreationObserver(todoListPageViewModel);
//        taskRepository.addUpdateObserver(todoListPageViewModel);
    }

    private MonthlyCalendarViewModel makeMonthlyCalendarViewModel() {
        return new MonthlyCalendarViewModel(servicesFactory.makeEventCreator(), servicesFactory.makeEventGetter(),
                servicesFactory.makeEventUpdater(), servicesFactory.makeEventSaver());
    }

    private WeeklyCalendarViewModel makeWeeklyCalendarViewModel() {
        return new WeeklyCalendarViewModel(servicesFactory.makeEventCreator(), servicesFactory.makeEventGetter(),
                servicesFactory.makeEventUpdater(), servicesFactory.makeEventSaver());
    }

    public MonthlyCalendarViewModel getMonthlyCalendarViewModel() {
        return monthlyCalendarViewModel;
    }

    public WeeklyCalendarViewModel getWeeklyCalendarViewModel() {
        return weeklyCalendarViewModel;
    }

    public TodoListPageViewModel getTodoListPageViewModel() { return todoListPageViewModel; }

    public AddTaskPageViewModel getAddTaskPageViewModel() {
        return addTaskPageViewModel;
    }
}
