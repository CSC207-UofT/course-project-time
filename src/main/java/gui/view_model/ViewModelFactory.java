package gui.view_model;


import data_gateway.event.ObservableEventRepository;
import data_gateway.task.ObservableTaskRepository;

public class ViewModelFactory {

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;

    public ViewModelFactory(ObservableEventRepository eventRepository, ObservableTaskRepository observableTaskManager) {
        this.monthlyCalendarViewModel = new MonthlyCalendarViewModel(eventRepository);
        this.weeklyCalendarViewModel = new WeeklyCalendarViewModel(eventRepository);
    }

    public MonthlyCalendarViewModel getMonthlyCalendarViewModel() {
        return monthlyCalendarViewModel;
    }

    public WeeklyCalendarViewModel getWeeklyCalendarViewModel() {
        return weeklyCalendarViewModel;
    }

}
