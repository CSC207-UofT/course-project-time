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

#### Junru

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
