package gui.view_model;


import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskRepository;
import services.services_factory.ObservableRepositoryFactory;

public class ViewModelFactory {

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;
    private final TodoListPageViewModel todoListPageViewModel;

    public ViewModelFactory(ObservableEventRepository eventRepository,
                            ObservableTaskRepository taskRepository) {
        this.monthlyCalendarViewModel = new MonthlyCalendarViewModel(eventRepository);
        this.weeklyCalendarViewModel = new WeeklyCalendarViewModel(eventRepository);
        this.todoListPageViewModel = new TodoListPageViewModel(taskRepository);
    }

    public ViewModelFactory(ObservableRepositoryFactory repositoryFactory) {
        this(repositoryFactory.makeEventRepository(), repositoryFactory.makeTaskRepository());
    }

    public MonthlyCalendarViewModel getMonthlyCalendarViewModel() {
        return monthlyCalendarViewModel;
    }

    public WeeklyCalendarViewModel getWeeklyCalendarViewModel() {
        return weeklyCalendarViewModel;
    }

    public TodoListPageViewModel getTodoListPageViewModel() { return todoListPageViewModel; }

}
