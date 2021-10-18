## Scenario Walk-through

The application will be started by running the main method in ApplicationDriver,
which interacts with the user. The application displays the available actions that 
the user can perform:

* View all events
* View all tasks
* Create a new task
* Create a new event
* Auto-schedule a task (not implemented in phase0)
* Manually schedule a task (not implemented in phase0)

The user inputs the selected action in the command line. In order to execute the action,
the ApplicationDriver calls methods provided in the MainController.

As a Facade, the MainController holds an instance of an EventController, a TaskController,
and a TaskToEventController (not used for phase 0).
When the ApplicationDriver calls methods of the MainController, the requests (to view or create
Events or Tasks, or schedule tasks) are passed on to the EventController and TaskController as the MainController
calls their methods.

The EventController stores an EventGetter, an EventAdder, and an AccessCalendarData that stores a Calendar. 
* If the user chooses to view all events, the EventController will call EventGetter to 
retrieve the events from the Calendar, which is retrieved from the AccessCalendarData. The EventGetter returns 
events' data in a List of Map format. The EventController passes the 
data back to the MainController, then the ApplicationDriver, where it displays the events
retrieve a list of events,
* If the user chooses to create a new event, the ApplicationDriver prompts the user for 
and parses the input necessary for event creation. The input is passed to the MainController,
then the EventController, which calls the EventAdder to create an event. The event will then
be added to the Calendar through AccessCalendarData.

The TaskController stores an instance of a TaskGetter, a TaskAdder, and an AccessTodoData. It 
works in a similar way as the EventController, but manages actions related to the
TodoList and Task instead. Viewing tasks and creating tasks are handled by this controller.
