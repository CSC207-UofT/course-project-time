package gui.view_model;


import gui.model.EventModelManager;

public class ViewModelFactory {

    private final MonthlyCalendarViewModel monthlyCalendarViewModel;
    private final WeeklyCalendarViewModel weeklyCalendarViewModel;

    public ViewModelFactory(EventModelManager manager) {
        this.monthlyCalendarViewModel = new MonthlyCalendarViewModel(manager);
        this.weeklyCalendarViewModel = new WeeklyCalendarViewModel(manager);
    }

    public MonthlyCalendarViewModel getMonthlyCalendarViewModel() {
        return monthlyCalendarViewModel;
    }

    public WeeklyCalendarViewModel getWeeklyCalendarViewModel() {
        return weeklyCalendarViewModel;
    }

}
