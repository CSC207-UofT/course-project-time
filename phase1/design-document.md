## SOLID (not completed)

How well does your design adhere to the SOLID design principles?
Give us specific examples of how your design adheres to the SOLID principles.
If you found that something in your design wasn't good, tell us about that too!
Pretending part of your design is good — when you know it isn't — can potentially hurt your mark significantly!
Acknowledging bad design can earn you marks and demonstrates understanding — especially if you discuss how you could fix it if you had more time!


###  The Single-responsibility principle

Classes are designed such that each only has one responsibility. For instance,
we have the functionalities to retrieve tasks and create new tasks. Even though both
functionalities are manipulation of tasks, we separated them into two clases, `TaskGetter`
and `TaskAdder` to be in charge of each functionality.

### The Open–closed principle


### The Liskov substitution principle
Our classes do not modify or remove behaviours from the interface they are implementing. An example of this is `EntityEventManager` which extends `CalendarManager`. `EntityEventManager` has all of the behaviours from `CalendarManager` and also extends it by adding the loadEvents and saveEvents methods.

### The Interface segregation principle
The interfaces we created are quite small thereby allowing them to be fully used by the classes. For example `EventInfo` just has the methods to get the attributes relating to `Event` and nothing else. This makes our interfaces easy to understand and none of the concrete classes are forced to implement any unnecessary methods.


### The Dependency inversion principle

In order for inner layers of the clean architecture to communicate with outer layers,
we used the dependency inversion principle. For instance, when we want to present all
events, the `EventGetter` will be calls to perform this action. It will retrieve necessary information
from the data gateway and passes it to the presenter for it to present. However, 
`EventGetter` is a use case class, and it should not rely on a presenter, which belongs
to the interface adaptor layer. Hence, the `EventGetter` holds a reference of a `CalendarEventPresenter`, 
which is an interface that defines the interface of a presenter. The `EventGetter` is able to call the
method of a presenter to present all the event information, without knowing the concrete implementation
of the presenter.

## Clean Architecture

We organized our code according to the various layers as outlined in Clean Architecture, as can be seen in 
[this](https://drive.google.com/file/d/1MepffESg7WIG2lEm6N33ytD_fawoBvkP/view?usp=sharing) UML diagram. 
Arrows point from outer to inner layers, which is consistent with the dependency rule that says that outer layers 
can depend on inner layers but not vice versa. The imports in our files are consistent with clean architecture as well.

__A scenario walk through that demonstrates clean architecture__

Upon running the `ApplicationDriver` of `console_app`, user is prompted to choose an action. 
Assuming the user chooses to view all Events, `ApplicationDriver` passes this request to the `MainController`, which,
as a facade, passes the request to the `EventController`. `EventController` calls `EventGetter` to retrieve all the
events from the database by interacting with the database gateway, the `CalendarManager` interface. The gateway returns 
an instance of `CalendarEventModel`, which is a data transfer object. Next, the `EventGetter` will pass the 
retrieved information to a presenter to present the information. Since `EventGetter` is a use case class, it uses an 
interface `CalendarEventDisplayBoundary` which has a `presentAllEvents()` method. A presenter class `ConsoleEventPresenter`
implements the interface `CalendarEventDisplayBoundary` and the concrete implementation is not visible to the use case class
`EventGetter`. This ensures that inner layers are not dependent on outer layers of clean architecture. `EventGetter` then invokes
`presentAllEvents()` in `ConsoleEventPresenter`, which formats the data given by `EventGetter` and passes the formatted data to
`ApplicationDriver` to be displayed.

__Violation of clean architecture__

However, there may be one violation of clean architecture when interacting with the outer layer. 

In `ApplicationDriver` of `console_app`, it can be seen that if the user chooses action 5 or 6 
(to automatically or manually schedule a task as an event), the `MainController` will be called to present the list of 
tasks and at the same time, retrieve a mappings of the tasks' position in the list and their actual id. 
However, retrieval of output data from use cases should be a responsibility of presenters and in our code, 
the `MainController` is taking on roles of both a controller and a presenter.
Given more time, we will remove the responsibilities of receiving output data from the use case from the controller, 
to a presenter instead. 

## Design Patterns

We have used the **facade design pattern** with the `MainController` in the console application being 
the facade. It routes requests from the `ApplicationDriver` to the respective controllers and acts as a facade.
We chose to use the facade design pattern to separate concerns of which controllers to call away from
the `ApplicationDriver`, whose main responsibility is to interact with the user.

We are also using the **observer pattern** as part of our notification system which
notifies users if there is an upcoming event or task due. The implementation of the notification
system has not been merged into `main` and is currently in the `notif-system-rebased` branch.
In the notification system, we will have a `NotificationTracker` which keeps track of all notifications.
When it is time to notify the user, it will call the `update()` method of its observers, which will pass
on the notification outwards to the user. We chose this design pattern because with this, the `NotificationTracker`
will not need to be aware of the actors responsible for propagating the notification outwards. Also, we can easily 
add more observers who should be aware of when a notification needs to be sent out, without changing anything in 
`NotificationTracker`.

In the future, we could implement a **command design pattern** for the user interface. We plan to use JavaFX to replace the console
interface. In JavaFX, according to Oracle's documentation, actions performed by GUI elements are managed by EventHandlers,
which implement events in a method called handle. To implement a command design pattern, we could add a Command interface with
an execute command. The command interface could be implemented by several of our use cases, such as EventSaver, EventAdder, TaskAdder, and EventScheduler.
The business rules implemented by these use cases would be called in the execute command. Then, we would create a subclass of EventHandler,
that accepts a command, and executes that command in its handle method. In order to adhere to Clean Architecture, our controllers
would likely be responsible for creating this EventHandler, and proving it to the user interface, to prevent the UI from interacting with use cases.
After successfully implementing the command design pattern, our UI would be able to execute commands implemented by use cases,
without knowing anything about them.

## Use of GitHub Features

Our group utilised various features of Github to improve our efficiency and keep ourselves organized. 
We set up different **branches** when developing various features for our program. When a feature has been developed, 
a **pull request** will be made. We often have at least two reviewers to review the pull request, 
and more if it is a larger pull request. Reviewers of pull requests frequently provided feedback, which was implemented before
merging.

Also, we have taken note of various problems that we have to fix in the future by opening new **issues**. 

To better visualize our progress, we used **Projects** to keep track of our tasks. For each feature that we 
plan to develop, we created a “column” for it and added cards to the column to break down what we have to do, 
or to indicate things to take note of when developing that feature.

## Code Style and Documentation

We ensured that code warnings are resolved and documented most of the methods, especially those that are 
not clear on what they do at first glance. We have also documented some classes that we thought needed 
some explanation of its responsibilities. The methods that are not documente were the methods we felt had sufficient explanation in their names, such as getters and setters.

## Testing (not completed)

Are most components of your system tested? Are there any components that would be difficult to test due to your design?
We know time is tight in the project, so it is fine if you don't test everything if your group decides to focus on other aspects of the program, but we want to see evidence of testing.
A significant portion of your code should be tested to earn full marks for this (run your tests with coverage to check).

## Refactoring (not completed)

In pull request #36, we have refactored our code so that it follows clean architecture.

Is there evidence that your team has refactored code in a meaningful way during the project?
Point to specific Pull Requests!
This could even be applying a design pattern to code you wrote in phase 0!
Are there any obvious code smells still in your code that you missed fixing?

## Code Organization

In phase 0, we organized our code by layers (entities, use case etc). However, as more functionalities 
are added, this way of packaging becomes confusing. In pull request #68, we repackaged our code 
and organized classes by responsibilities. For example, all classes related to the interaction with 
the database are packaged under `data_gateway`, while classes that are specific to the command line 
interface are packaged under `console_app`.  

In the package `services`, we further organized the classes through inner packages. 
Each inner package contains classes that are responsible for the same functionalities, 
as their name suggests. For instance, `event_creation` is a package that contains all the 
classes needed to create a new event.

With this structure, it is easy to locate classes that interact closely together, since they 
are responsible for the same functionality, such as creation or presentation of tasks and events. 
Moreover, when functionalities are added or changed, the changes will likely be limited to a single package.

## Functionality

Our program fulfills most parts of our specification (see `phase0\specification.md`).
The specification seems to be sufficient for each of us to be actively involved in
a feature of the program in each phase. In phase 2, we still have to implement the functionality
to reschedule event, finalize the notification system, finalize pomodoro system, and set up the graphical user interface.

Currently, our program is able to support these functionalities:
Firstly, users can view all events and tasks, which are loaded into our program from json files.
Users are also able to create new events and tasks. Next, we can automatically schedule tasks as events
or let users manually input their desired time. Also, users can choose to save data, which
is to persist all the changes made in the current session. The changes will then be saved into the json files.
