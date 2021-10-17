## Progress Report

### Specification
This application help users manage their time and schedule by providing
two main functionalities: a todo-list to store all the tasks and a calendar 
to store all events. The application also enables auto-scheduling of tasks
an events to help users organize their time. Users can input tasks into the todo list, along with their deadline and anticipated duration. The user may also attach a time to the task, and place it in the calendar as an event. The program also offers to complete this process automatically. The process by which the 

### CRC model
Our CRC model consists of: 

**3 Entity** classes: `Task`, `Event` and `Calendar`.

**8 Use Case** classes: `TodoList`, `EventScheduler`, `EventGetter`, `TaskGetter`, `EventAdder`, `TaskAdder`, 
`TaskToEventManual` and `TaskToEventAuto`

**4 Controllers**: `MainController`, `EventController`, `TaskController`, `TaskEventConsoleController`


**4 interfaces**: `GetEvent`, `TaskToEvent`, `TaskEventAutoController`, `TaskEventManualController`

**User Interface**: `ApplicationDriver`



### Scenario Walk-through
The user will be able to: 
* View their events
* View all tasks
* Create a new task
* Create a new event
* Auto-schedule a task (turns it into an event)
* Manually schedule a task (turns it into an event)

### What each member has been working on and future plans
CRC cards were created by the whole team.

#### Emily
* Worked on `TaskToEvent` interface and classes with Valerie
* Created `EventScheduler` from Tahseen's `CalendarBuilder` that we decided to remove and replace with 
`EventScheduler`, `EventScheduler` also uses some methods from Craig's `Calendar` class
* Contributed to progress report and specification


#### Alexander
* Created basic command line interface
* Created `EventGetter` and `TaskGetter` classes
* Created `AccessCalendarData` and `AccessTodoData` classes
* Refactored `EventScheduler` to store calendar data in `AccessCalendarData`
* Refactored use case classes to access data from the accessor use cases.


#### Craig
* Created `Calendar` with Alexander
* Created `TaskToEventConsoleController`, `TaskToEventAuto`, `TaskToEventManual` classes (may be removed later for better design choice)
* Created `TaskEventAutoController` and `TaskEventManualController`
* Simple refactoring
* Revisited and planned higher level design choices

#### Junru
* Created `Task` and `Event` classes
* Created `TaskAdder` and `EventAdder` classes
* Added and refactored methods in other classes relating to the above four classes

#### Valerie
* Worked on `TaskToEvent` interface and classes with Emily
* Created `EventController`, `MainController`, and `TaskController` classes
* Created `ApplicationDriver` and made the CLI ready to use

#### Tahseen
* Worked on `TodoList` class
* Created `CalendarBuilder` class which was removed later in the development of the project
* Created tests for `Calendar` class, `Event` class, `Task` class, `TodoList` class, and `EventScheduler` class
* Exception handling for `ApplicationDriver` class

#### Jenci
* Created `TodoList` class
* Created `EventController`, `MainController`, and `TaskController` classes
* Created `ApplicationDriver` and made the CLI ready to use

#### Future Plans
* creating a notification system that will notify the user when their task is about to start
* the ability to reschedule an event if the user misses it, both automatically and manually (similar to the `TaskToEvent`
classes)
* Manually create an event from task
* Store data externally (most likely in a json file)
* Ability to save data to an ics file
* Add a visual user interface

### What has worked well so far with our design

When working on our project, we always communicate with each other to ensure that the
goals for the next stage is clear for everyone and that we adhere to the design principles
(i.e. SOLID and Clean Architecture). When implementing the classes, we strictly followed the
dependency rule of the Clean Architecture by enforcing the classes to only depend on other
classes of adjacent layers. One example is that when deciding where to temporarily store
our entities (before implementing any type of long-term storage), we did not take the shortcut and
store them inside the controllers; instead, we made use cases classes to store our entities.
This can prevent dependency-related bugs to occur in the future when we
make major changes to our existing code (e.g. introducing a long-term storage).


### What we are struggling with

Our group had difficulty with the instantiation of entities and their storage in 
memory. We do not have a database for our program, and we had trouble deciding a clean way of 
creating a `Calendar` object that is accessible to multiple use cases.
We wanted to avoid storing instances of entities in Controllers, since 
that would make the Controller dependent on the entity, thereby
violating the dependency rule. We also did not want individual use cases
storing copies of the same entity, as to avoid duplication. Robert Martin 
outlines a potential solution for clean instantiation of entities
on page 101 of Clean Architecture. The solution involves a factory that creates 
and returns instantiated entities. Our main method could interact with the factory
and these entities via interfaces. However, our skeleton program only involves 
one calendar and todo list, and we decided that the implementation of a calendar 
factory was unnecessary for our skeleton project. Ultimately We decided to store 
our calendar and todolist in a single use case, that other use cases can access. 
This method is likely to change, especially once we begin storing data in the 
data layer of our application.

Currently, both `Task` and `TodoList` are tracking the completion status of tasks,
but we aren't sure yet which one should be responsible for it.
