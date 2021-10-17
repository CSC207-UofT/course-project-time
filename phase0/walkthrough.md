## Scenario Walk-through

The application will be started by running the main method in ApplicationDriver,
which interacts with the user. The application displays the available actions that 
the user can perform:

* View all events
* View all tasks
* Create a new task
* Create a new event
* Auto-schedule a task
* Manually schedule a task

The user inputs the selected action in the command line. In order to execute the action,
the ApplicationDriver calls methods provided in the MainController.

As a Facade, the MainController holds an instance of an EventController, a TaskController,
and a TaskToEventController.
When the ApplicationDriver calls methods of the MainController, the requests (to view or create
Events or Tasks, or schedule tasks) are passed on to the EventController and TaskController as the MainController
calls their methods.

The EventController stores an EventGetter and an EventAdder. It also stores
an instance of a Calendar which is passed to EventGetter and EventAdder when they are instantiated. 
* If the user chooses to view all events, the EventController will call EventGetter to 
retrieve the events from the Calendar. The EventGetter retrieve all events from the
Calendar and returns them in a List of Map format. The EventController passes the 
data back to the MainController, then the ApplicationDriver, where it displays the events
retrieve a list of events,
* If the user chooses to create a new event, the ApplicationDriver prompts the user for 
and parses the input necessary for event creation. The input is passed to the MainController,
then the EventController, which calls the EventAdder to create an event. The event will then
be added to the Calendar.

The TaskController stores an instance of a TodoList, TaskGetter, and TaskAdder, with 
works in a similar way as the EventController, but manages actions related to the
TodoList and Task instead. Viewing tasks and creating tasks are handled by this controller.

Lastly, the scheduling of tasks are handled by the TaskToEventController.
If the user chooses to auto-schedule a task, the ApplicationDriver prompts the user to
choose a task from the list of all tasks. The ApplicationDriver then calls the MainController
which calls the TaskToEventController to ...

If the user chooses to manually schedule a task, the user will be prompted
to choose a task from the list of all tasks and input a time. ...
