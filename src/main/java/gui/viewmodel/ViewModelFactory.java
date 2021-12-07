package gui.viewmodel;


import services.servicesfactory.ObservableRepositoryFactory;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;

public class ViewModelFactory {

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;
    private final TodoListPageViewModel todoListPageViewModel;
    private final ObservableRepositoryFactory repositoryFactory;

    public ViewModelFactory(ObservableRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
        ObservableEventRepository eventRepository = repositoryFactory.makeEventRepository();
        ObservableTaskRepository taskRepository = repositoryFactory.makeTaskRepository();

        this.monthlyCalendarViewModel = new MonthlyCalendarViewModel(eventRepository);
        this.weeklyCalendarViewModel = new WeeklyCalendarViewModel(eventRepository);
        this.todoListPageViewModel = new TodoListPageViewModel(taskRepository);
        addObserversToRepositories();
    }

    private void addObserversToRepositories() {
        ObservableEventRepository eventRepository = repositoryFactory.makeEventRepository();
        ObservableTaskRepository taskRepository = repositoryFactory.makeTaskRepository();

        eventRepository.addCreationObserver(monthlyCalendarViewModel);
        eventRepository.addUpdateObserver(monthlyCalendarViewModel);
        eventRepository.addCreationObserver(weeklyCalendarViewModel);
        eventRepository.addUpdateObserver(weeklyCalendarViewModel);

        taskRepository.addCreationObserver(todoListPageViewModel);
        taskRepository.addUpdateObserver(todoListPageViewModel);
    }

    public MonthlyCalendarViewModel getMonthlyCalendarViewModel() {
        return monthlyCalendarViewModel;
    }

    public WeeklyCalendarViewModel getWeeklyCalendarViewModel() {
        return weeklyCalendarViewModel;
    }

    public TodoListPageViewModel getTodoListPageViewModel() { return todoListPageViewModel; }

}
