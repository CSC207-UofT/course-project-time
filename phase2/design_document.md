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


### The Open–closed principle


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

__A scenario walk through that demonstrates clean architecture__



## Design Patterns



## Use of GitHub Features (add in github actions)

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
some explanation of its responsibilities. The methods that are not documented were the methods we felt had
sufficient explanation in their names, such as getters and setters.

## Testing (give an example)

Most of the components are easy to test because we follow the clean architecture closely and ensured that
classes are decoupled. Many of the classes rely on other classes through interfaces.
This makes our code easy to test as we can easily create a mock class that implements the
interface that the class is using.

## Refactoring




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
for the frontend, we then decided to seperate the classes into `view`, `viewmodel`, and a `utility` package
with helper classes. `model` was left out as we were using the gateway classes directly as our `model` in the
MVVM pattern.