
## SOLID

###  The Single-responsibility principle

Classes were designed such that each only has one responsibility. For instance,
we have the functionalities to retrieve tasks and create new tasks. Even though both
functionalities are manipulation of tasks, we separated them into two classes, `EventGetter`
and `EventAdder` to be in charge of each functionality. In phase 2, we refactored the 
code further to separate `EventGetter` into `EventGetter` and `EventOutputter`, such that
`EventGetter` is only in charge of getting event data from the database, whereas the
responsibility of sending the data out to be presented to the user is isolated in `EventOutputter`, 
satisfying SRP.

In our model view system, Each view is dependent on a designated model view, through a binding system. One could argue that some responsibilities currently enacted by the model view could be handled by a separate controller


### The Open–closed principle
Classes were written such that they are easy to extend. We have been avoiding extensive
conditional control flow in lower layers in favor of classes. Although some classes
are still concrete, interfaces are easily extractable which allows us to take advantage
of polymorphism. Across phases 1 and 2, we have added a notification system to our program, which 
is to notify users when an event or task deadline is upcoming. Notifications might seem to be a concept 
bundled with events and tasks, but it was not designed as an attribute when we first drafted the CRC
model for `Event` and `Task`. In order to follow OCP, we created a `Notification` entity class which 
references the `id` attribute of `Event` and `Task` instead, and all classes and interfaces written were
an extension of existing classes. `EventNotificationCreationModel` which extends `CalendarEventModel`
and `EventNotificationCreationBoundary` which extends `CalendarEventCreationBoundary` are examples of how we 
followed OCP in the implementation of the notification system.

### The Liskov substitution principle
Our classes do not modify or remove behaviours from the interface they are
implementing. In the phase 1 design document, we have talked about how `EntityEventManager`
is an example of this as not only does it implement the interface `CalendarManager`, but
it also extends the interface's methods by adding the `loadEvents` and `saveEvents`
methods. Our added functionalities in phase 2 continues to show that LSP has been followed .
For example, when creating the GUI in phase 2, we have wanted the "model" of the MVVM 
to be an observable repository which allows the view model to access the data in the database. Instead of modifying 
existing code. Instead of editing current implementations of the gateway classes to fulfil our needs, 
we created a new interface `ObservableTaskRepository` which extends the previous 
interface for the manager, `TodoListManager`. We have avoided extending concrete classes as this structure tends to create
method overriding that violates LSP. Moreover, it shows that our code has been following LSP,
hence allowing easy extension.


### The Interface segregation principle (can be further explained)
The interfaces we created are quite small thereby allowing them to be fully
used by the classes. For example `EventInfo` just has the methods to get the
attributes relating to `Event` and nothing else. This makes our interfaces
easy to understand and none of the concrete classes are forced to implement
any unnecessary methods. 


### The Dependency inversion principle

In order for inner layers of the clean architecture to communicate with outer layers,
we used the dependency inversion principle. For instance, when we want to present all
events, the `EventOutputter` will be called to perform this action. It passes data from the database
to the presenter for it to present. However, `EventOutputter` is a use case class, and it should not rely on a presenter, which belongs
to the interface adaptor layer. Hence, the `EventOutputter` holds a reference of a `CalendarEventPresenter`,
which is an interface that defines the interface of a presenter. The `EventOutputter` is able to call the
method of a presenter to present all the event information, without knowing the concrete implementation
of the presenter.

## Clean Architecture

We organized our code according to the various layers as outlined in Clean Architecture, as can be seen in
[this](https://drive.google.com/file/d/1MepffESg7WIG2lEm6N33ytD_fawoBvkP/view?usp=sharing) early design UML diagram.
Arrows point from outer to inner layers, which is consistent with the dependency rule that says that outer layers
can depend on inner layers but not vice versa. The imports in our files are consistent with clean architecture as well.

One source of confusion might come from the use of the word Controllers to describe aspects of our view. In JavaFx, the UI is handled by assigned controllers.
These controllers are responsible for UI operations, like delegating operations when a button is pressed, which are not the same operations that a controller handles in Clean Architecture. We decided to stick to the naming convention of controllers, to align with JavaFx's system, however they operate solely in the Frameworks and Drivers layer. Many of the controllers in Clean Architecture for our UI can be found in the ViewModels. The UI delegates tasks to these ViewModels, which interact with use cases. These use cases are built through a factory system, which also injects them with our data access interfaces.

In the graphical user interface that we have implemented in phase 2, we have used the MVVM pattern to decouple
the user interface and the rest of the application.

We have also followed clean architecture in the data persistence layer.

__A scenario walk through that demonstrates clean architecture__



## Design Patterns

In the command line interface that we used in phase 1, 
we have used the **facade design pattern** with the `MainController` in `consoleapp` being
the facade. It routes requests from the `ApplicationDriver` to the respective controllers and acts as a facade.
We chose to use the facade design pattern to separate concerns of which controllers to call away from
the `ApplicationDriver`, whose main responsibility is to interact with the user.

We have used the **observer pattern** as part of our notification system which
notifies users if there is an upcoming event or task due. `NotificationTracker` in `services\notification` package
keeps track of all notifications. When it is time to notify the user, it will call the `presentNotification()` method 
of its observers, which are presenters (in the clean architecture jargon). These presenters present the notification
in various ways (through email and on desktop), which is abstracted away from the `NotificationTracker`.
We chose this design pattern because the actors responsible for sending out the notifications
should not have control over `NotificationTracker`, nor should it be exposed to the tracking responsibilities
or the tracker. More importantly, it allows us to define a one-to-many dependency between the tracker
and the presenters without making them tightly coupled. With this design, it would be easy to add another way of presenting
notifications, or removing existing ones, ensuring flexibility.

In the notification system, we are also using the **proxy pattern** to add an event to the program,
together with its notification time. In phase 0, we only have an `EventAdder` in `services\eventcreation` package,
which adds an event to the program. As mentioned in the OCP section, we created an additional entity `Notification`
which has reference to the `id` of an `Event`, so the event creation process would also need
to create a `Notification` instance that corresponds to the event. This design pattern was chosen as we
needed a wrapper of the `EventAdder`, that both creates an event and a task, but we needed to hide this creation
process from the client code. Proxy would be the most suitable choice here, allowing the client code to remain
the same, while getting the `Notification` instance created.

As part of refactoring of our code (see the section on refactoring), we have used the **factory method** to created 
factories that are responsible for class creation and managing dependencies between classes. In the package `services\servicefactory`,
it can be seen that we have `RepositoryFactory`, `ObservableRepositoryFactory`, and `ServicesFactory`.
Not only does this decouples our code since classes that uses other classes do not need to be exposed to manage the 
dependencies used by the classes used, but it also allows us to save system resources since it allows us to
reuse existing objects. As can be since from the implementations of the factories' interfaces, cached instances are
returned, instead of creating new ones. Moreover, it becomes easy to extend our program as we can create new concrete
factories that extend the existing ones, and change the products returns, where the altered products are an extension of
what the original factories return. An example of the ease of extension can be seen in `NotificationServiceFactory`. 
In phase 2, when we introduced the notification system, we had to have event creation classes that will also create a
notification instance (refer to the section on OCP for explanation of why is this so). However, the `BasicServiceFactory`
did not provide us with the required event creation class. Therefore, we created another implementation of `ServicesFactory`
that solved the issue. This shows how easily extendable our code it, with the help of this chosen design pattern.

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

To ensure that our pull requests wouldn't break our program when merged in, we used github actions to run our test suite every time
a PR was opened, reopened, marked ready for review, or committed to. This allowed us to quickly and easily see what needed to be fixed. 
We implemented this by changing our project to a Gradle project, which was done by adding a build.gradle file. 

## Code Style and Documentation

We ensured that most of our code warnings are resolved. The only two code warnings are in `MonthlyCalendarController`
and `WeeklyCalendarController`, with `Entry<String>` marked as an unchecked cast when we tried to cast Entry<?> as 
Entry<String>. However, this would not be an issue because the only usage of Entry<?> in our application is Entry<String>. 

We documented most of the methods, especially those that are
not clear on what they do at first glance. We have also documented some classes that we thought needed
some explanation of its responsibilities. The methods that are not documented were the methods we felt had
sufficient explanation in their names, such as getters and setters.

## Testing

Most of the components are easy to test because we follow the clean architecture closely and ensured that
classes are decoupled. Many of the classes rely on other classes through interfaces.
This makes our code easy to test as we can easily create a mock class that implements the
interface that the class is using. An example is `TaskInfoFromTaskReaderTest`, which tests the class
`TestInfoFromTaskReader`. The class being tested depends on `TaskReader`, which is an interface. By
creating a mock-class of the `TaskReader` interface, the tests become very straightforward since we
do not need to worry about dependencies in our tests, where on the other hand if `TaskReader` was to be a
concrete class, we would also have to worry about the classes which `TaskReader` depend on as well.
Additionally, since interfaces are a lot less likely to be changed rather than concrete classes implementing
them, the tests we wrote are resistant to changes, and therefore is effective as long as there are
no major changes on the interfaces side,

Coverage-wise, our tests covers almost all methods in the `services` package. All of our tests passing guarantees
that the classes are working properly. If any use cases are changed and there is a subtle bug, our tests could
effectively locate the bug and determine the cause, saving much debugging time and avoiding much nuisance.

## Refactoring

In phase 1, we mentioned that we refactored the code to follow clean architecture more closely,
by introducing clear service layer interfaces to helper decouple the application domain
and the program that uses it. This was implemented in pull request #36.

In phase 2, we continued to remove concrete dependencies by introducing more interfaces. 
This made us separate existing classes into smaller ones, to follow SRP. As can be seen in 
pull requests [#110](https://github.com/CSC207-UofT/course-project-time/pull/110) and 
[#111](https://github.com/CSC207-UofT/course-project-time/pull/111), we seperated `TaskGetter` and `EventGetter`
into two smaller classes for retrieval of data and outputting data respectively. At the same time, we add interfaces
to decouple them from the rest of the application. 

In phase 2, most of our refactoring was focused on moving the responsibility of class creation to factories.
As mentioned in the phase 1 report, the refactoring that moved us towards clean architecture has 
pushed a large amount of class creation into the controllers, specifically `MainController` in `consoleapp`.
In the files of pull request [#109](https://github.com/CSC207-UofT/course-project-time/pull/109/files), it can 
be seen that `MainController` previously had a long constructor, since it was creating all the dependencies needed.
However, `MainController` should not be responsible for creating these dependencies, as its main role was to 
act as a facade, as mentioned in the design pattern section.

In pull request [#109](https://github.com/CSC207-UofT/course-project-time/pull/109), we created factories that are 
responsible for class creation. In `services\servicefactory`, it can be seen that we 
separated the factories into interfaces `ServicesFactory` and `RepositoryFactory`, 
together with its implementations. Once again, interfaces was used to ensure that these factories are not tightly 
coupled with other classes. More explanation of the factories was done in the design patterns section.


## Code Organization

In phase 0, we organized our code by layers (entities, use case etc). However, as more functionalities
are added, this way of packaging becomes confusing. In pull request #68 in phase 1, we repackaged our code
and organized classes by a combination of layers and components. CLasses were mainly organized by layer,
such as the view being the `consoleapp`, the data gateway in `datagateway` etc. We decided to further
break down the classes in the use case layer by responsibilities. All use case classes are packaged
under `services`, where we seperated them further to responsibilities. For instance, `eventcreation` 
is a package that contains all the classes needed to create a new event.

With this structure, it is easy to locate classes that interact closely together, since they
are responsible for the same functionality, such as creation or presentation of tasks and events.
Moreover, when functionalities are added or changed, the changes will likely be limited to a single package.

In phase 2, when the GUI was added in, it was easy to organize the code by having all classes and interfaces
related to the GUI in the `gui` package which represents the view. Since we were utilizing the MVVM pattern
for the frontend, we then decided to separate the classes into `view`, `viewmodel`, and a `utility` package
with helper classes. `model` was left out as we were using the gateway classes directly as our `model` in the
MVVM pattern.
