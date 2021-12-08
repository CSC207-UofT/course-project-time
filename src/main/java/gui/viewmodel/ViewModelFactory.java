package gui.viewmodel;


import services.servicesfactory.ObservableRepositoryFactory;
import datagateway.event.ObservableEventRepository;
import datagateway.task.ObservableTaskRepository;
import services.servicesfactory.ServicesFactory;

public class ViewModelFactory {

    private final ServicesFactory servicesFactory;
    private final ObservableEventRepository eventRepository;
    private final ObservableTaskRepository taskRepository;

    private MonthlyCalendarViewModel monthlyCalendarViewModel;
    private WeeklyCalendarViewModel weeklyCalendarViewModel;
    private TodoListPageViewModel todoListPageViewModel;
    private AddTaskPageViewModel addTaskPageViewModel;
    private TaskPageViewModel taskPageViewModel;

    private final TaskDataBinding taskDataBinding = new TaskDataBinding();
    private SettingsViewModel settingsViewModel;

    public ViewModelFactory(ObservableRepositoryFactory repositoryFactory, ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
        eventRepository = repositoryFactory.makeEventRepository();
        taskRepository = repositoryFactory.makeTaskRepository();
    }

    public MonthlyCalendarViewModel getMonthlyCalendarViewModel() {
        if (monthlyCalendarViewModel == null) {
            monthlyCalendarViewModel = new MonthlyCalendarViewModel(servicesFactory.makeEventCreator(),
                    servicesFactory.makeEventGetter(), servicesFactory.makeEventUpdater(), servicesFactory.makeEventSaver());
            eventRepository.addCreationObserver(monthlyCalendarViewModel::handleCreation);
            eventRepository.addUpdateObserver(monthlyCalendarViewModel::handleUpdate);
        }
        return monthlyCalendarViewModel;
    }

    public WeeklyCalendarViewModel getWeeklyCalendarViewModel() {
        if (weeklyCalendarViewModel == null) {
            weeklyCalendarViewModel = new WeeklyCalendarViewModel(servicesFactory.makeEventCreator(),
                    servicesFactory.makeEventGetter(), servicesFactory.makeEventUpdater(), servicesFactory.makeEventSaver());
            eventRepository.addCreationObserver(weeklyCalendarViewModel::handleCreation);
            eventRepository.addUpdateObserver(weeklyCalendarViewModel::handleUpdate);
        }
        return weeklyCalendarViewModel;
    }

    public TodoListPageViewModel getTodoListPageViewModel() {
        if (todoListPageViewModel == null) {
            todoListPageViewModel = new TodoListPageViewModel(servicesFactory.makeTaskGetter(), taskDataBinding);
            taskRepository.addCreationObserver(todoListPageViewModel::handleCreation);
            taskRepository.addUpdateObserver(todoListPageViewModel::handleUpdate);
            taskRepository.addDeleteObservers(todoListPageViewModel::handleDeletion);
        }
        return todoListPageViewModel;
    }

    public AddTaskPageViewModel getAddTaskPageViewModel() {
        if (addTaskPageViewModel == null) {
            addTaskPageViewModel = new AddTaskPageViewModel(servicesFactory.makeTaskCreator());
        }
        return addTaskPageViewModel;
    }

    public TaskPageViewModel getTaskPageViewModel() {
        if (taskPageViewModel == null) {
            taskPageViewModel = new TaskPageViewModel(servicesFactory.makeTaskGetter(), servicesFactory.makeTaskUpdater(),
                    servicesFactory.makeTaskDeleter());
            taskDataBinding.addObserver(taskPageViewModel);
        }
        return taskPageViewModel;
    }
    public SettingsViewModel getSettingViewModel() {
        if (settingsViewModel == null) {
            settingsViewModel = new SettingsViewModel(servicesFactory.makeICSSaver());
        }
        return settingsViewModel;
    }

}
