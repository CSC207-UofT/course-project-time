package gui.view_model;


import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskRepository;

public class ViewModelFactory {

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;
    private final TodoListPageViewModel todoListPageViewModel;
    private final MainPageViewModel mainPageViewModel;

    public ViewModelFactory(ObservableEventRepository eventRepository,
                            ObservableTaskRepository taskRepository) {
        this.monthlyCalendarViewModel = new MonthlyCalendarViewModel(eventRepository);
        this.weeklyCalendarViewModel = new WeeklyCalendarViewModel(eventRepository);
        this.todoListPageViewModel = new TodoListPageViewModel(taskRepository);
        this.mainPageViewModel = new MainPageViewModel(taskRepository, eventRepository);
    }

    public MonthlyCalendarViewModel getMonthlyCalendarViewModel() {
        return monthlyCalendarViewModel;
    }

    public WeeklyCalendarViewModel getWeeklyCalendarViewModel() {
        return weeklyCalendarViewModel;
    }

    public TodoListPageViewModel getTodoListPageViewModel() { return todoListPageViewModel; }

    public MainPageViewModel getMainPageViewModel()  { return mainPageViewModel; }

}
