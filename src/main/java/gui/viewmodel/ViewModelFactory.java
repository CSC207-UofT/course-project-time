package gui.viewmodel;


import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;

public class ViewModelFactory {

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;
    private final TodoListPageViewModel todoListPageViewModel;
    private final AddTaskPageViewModel addTaskPageViewModel;

    public ViewModelFactory(ObservableEventRepository eventRepository,
                            ObservableTaskRepository taskRepository) {
        this.monthlyCalendarViewModel = new MonthlyCalendarViewModel(eventRepository);
        this.weeklyCalendarViewModel = new WeeklyCalendarViewModel(eventRepository);
        this.todoListPageViewModel = new TodoListPageViewModel(taskRepository);
        this.addTaskPageViewModel = new AddTaskPageViewModel(taskRepository);
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
