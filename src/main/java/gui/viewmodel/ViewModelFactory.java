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
    private SettingsViewModel settingsViewModel;
    private MainPageViewModel mainPageViewModel;
    private TaskPageViewModel taskPageViewModel;

    private final TaskDataBinding taskDataBinding = new TaskDataBinding();

    public ViewModelFactory(ObservableRepositoryFactory repositoryFactory, ServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
        eventRepository = repositoryFactory.makeEventRepository();
        taskRepository = repositoryFactory.makeTaskRepository();
    }

    public CalendarViewModel getMonthlyCalendarViewModel() {
        if (calendarViewModel == null) {
            calendarViewModel = new CalendarViewModel(servicesFactory.makeEventCreator(),
                    servicesFactory.makeEventGetter(), servicesFactory.makeEventUpdater(), servicesFactory.makeEventSaver());
            eventRepository.addCreationObserver(calendarViewModel::handleCreation);
            eventRepository.addUpdateObserver(calendarViewModel::handleUpdate);
        }
        return calendarViewModel;
    }

    /**
     * Returns a view model for the weekly calendar view, which is implemented as being the same as that of
     * the monthly calendar view.
     * @return a view model
     */
    public CalendarViewModel getWeeklyCalendarViewModel() {
        return getMonthlyCalendarViewModel();
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

    public MainPageViewModel getMainPageViewModel()  {
        if (mainPageViewModel == null) {
            mainPageViewModel = new MainPageViewModel(servicesFactory.makeTaskGetter(), servicesFactory.makeTaskSaver(),
                    servicesFactory.makeEventGetter(), servicesFactory.makeEventSaver());
            taskRepository.addCreationObserver(mainPageViewModel::handleCreation);
            taskRepository.addUpdateObserver(mainPageViewModel::handleUpdate);

            eventRepository.addCreationObserver(mainPageViewModel::handleCreation);
            eventRepository.addUpdateObserver(mainPageViewModel::handleUpdate);
        }
        return mainPageViewModel;
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
