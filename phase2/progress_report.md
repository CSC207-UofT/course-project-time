#### Emily
During phase 2, I worked on the pomodoro timer and also the desktop notification presenter.

In pull requests [#127](https://github.com/CSC207-UofT/course-project-time/pull/127) and [#140](https://github.com/CSC207-UofT/course-project-time/pull/140), 
I worked on the pomodoro system. 
The first one was done with Xandar, which implemented most of the pomodoro system including error checking. However, it did not allow the user to leave and return to the pomodoro page without the timer restarting. This is fixed in the second pull request. Here, I created a system that saves where the user is at in the timer and when they return
to the pomodoro page, it calculates how much time has passed and whether they are now on a work or break interval. think these were both significant contributions as without them, the pomodoro feature would not be in the final project.

#### Alexander

During phase 2, I worked on the pomodoro and settings pages in the GUI. Additionally, I added a system for exporting data from our calendar into an ICS file. 
I also worked on updates for serialization of notifications and setting. However, these changes were not connected to the front end.

In pull request [#127](https://github.com/CSC207-UofT/course-project-time/pull/127), in collaboration with Emily, I created a front end page for our Pomodoro clock, and implemented the logic for the clock's visuals. 
I think it demonstrates a significant contribution to the front end functionality of our program. I also worked collaboratively with Tahseen to develop the settings page.

My contributions to pull request [#164](https://github.com/CSC207-UofT/course-project-time/pull/164) included the ICS serializer and the controllers/gateways that support it. 
I think my contributions to this PR demonstrate an implementation of a new feature across multiple layers of Clean Architecture.



#### Craig

In Phase 2 I added the `DateStrategy`, which allows us to offer users to create rules for when events should reoccur
rather than storing a finite list. These changes were introduced in [#95](https://github.com/CSC207-UofT/course-project-time/pull/95)
but only replacing previous static-date behavior here, and then later fully incorporated into the service layer in
[#165](https://github.com/CSC207-UofT/course-project-time/pull/165).

I also introduced the observer family of repositories to allow for more up-to-date data syncing between the
view model and domain repositories.

Outside of those, I helped maintain and manage the dependencies and structure of our code in various PRs throughout
the process. This includes introducing factories, removing middle man classes, resolving features with poor source code
dependencies (like creating Events from Tasks used to be all over the place).


#### Junru
During phase 2, I mainly worked on notification system development and GUI.

I worked on notification system with Valerie. We designed the [UML diagram](https://drive.google.com/file/d/1d6-EMS59UJDCOAwQ8ZjQiKCUdbTaeNX0/view) and implemented the code. We divided the code into several PRs based on clean architecture, and [#141](https://github.com/CSC207-UofT/course-project-time/pull/141) is mainly the use case part of the notification system code. This PR is not merged for phase 2 since the front end have not been implemented for notification system.

I also worked on GUI design. I'm mainly responsible for the todo list page display, making the most recent tasks appear on the top, and making the completed tasks display in a different way. [#132](https://github.com/CSC207-UofT/course-project-time/pull/132/files) includes the initial design of the todo list page.
Besides, I also worked with Tahseen for the design of the main page.

#### Valerie

Since phase 1, I have been working on finalizing the notification system with Junru, and participating
in the design of it through drafting its [UML diagram](https://drive.google.com/file/d/1d6-EMS59UJDCOAwQ8ZjQiKCUdbTaeNX0/view).

Apart from that, I have mainly contributed to the creation of the graphical user interface, by participating
in the decision of choosing MVVM as the pattern to be used for the presentation layer, creating the views of the
calendar pages, and applying factory method to create view models.

The factory method was implementing in pull request [#126](https://github.com/CSC207-UofT/course-project-time/pull/126).
This is significant as it separates the concerns of managing dependencies of various view models from each Javafx controller
(the view) to the `ViewModelFactory`, making the code less tightly coupled. This also ensures that our code is
easily extensible, as future addition of views and view models, as all the class creation logic is centralized in the factory. 


#### Tahseen

#### Jenci

Since Phase 1, I mainly have been working on two things: writing tests and developing the GUI. For the test part, 
I wrote tests for multiple methods in the `services` package. For the GUI part, I made the task creation page and the 
task modification page.

https://github.com/CSC207-UofT/course-project-time/pull/161

This pull request is the implementation of the task modification page, allowing tasks to be modified on the front end 
and the changes reflected to the back end. This demonstrates a significant contribution to the team since this feature 
is a crucial part of our front end, and the code (i.e. view and viewmodel) also serves as a foundation to some of the 
features implemented later, such as task deletion and scheduling task into an event.

https://github.com/CSC207-UofT/course-project-time/pull/121

This pull request consists of 4 tests I added. To make the size of pull requests reasonable, I did not cram all the 
test suites in one pull request. Along with the other testing-oriented pull requests, I made a significant contribution 
to the testing aspect of the project. 

