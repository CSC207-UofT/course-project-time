package gui.viewmodel;


import services.servicesfactory.ObservableRepositoryFactory;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;

public class ViewModelFactory {

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;
    private final TodoListPageViewModel todoListPageViewModel;
    private final MainPageViewModel mainPageViewModel;
    private final AddTaskPageViewModel addTaskPageViewModel;

    public ViewModelFactory(ObservableEventRepository eventRepository,
                            ObservableTaskRepository taskRepository) {
        this.monthlyCalendarViewModel = new MonthlyCalendarViewModel(eventRepository);
        this.weeklyCalendarViewModel = new WeeklyCalendarViewModel(eventRepository);
        this.todoListPageViewModel = new TodoListPageViewModel(taskRepository);
        this.mainPageViewModel = new MainPageViewModel(taskRepository, eventRepository);
        this.addTaskPageViewModel = new AddTaskPageViewModel(taskRepository);
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

    public MainPageViewModel getMainPageViewModel()  { return mainPageViewModel; }

    public AddTaskPageViewModel getAddTaskPageViewModel() {
        return addTaskPageViewModel;
    }
}
