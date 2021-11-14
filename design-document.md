## SOLID (not completed)

How well does your design adhere to the SOLID design principles?
Give us specific examples of how your design adheres to the SOLID principles.
If you found that something in your design wasn't good, tell us about that too!
Pretending part of your design is good — when you know it isn't — can potentially hurt your mark significantly!
Acknowledging bad design can earn you marks and demonstrates understanding — especially if you discuss how you could fix it if you had more time!

## Clean Architecture (not completed)

Is your program consistent with Clean Architecture?
Show us with something like a CRC model or UML diagram.
Describe a scenario walk-through and highlight how it demonstrates Clean Architecture.
Are there any clear violations if we were to randomly look at the imports in a few of your files?
Is the Dependency Rule consistently followed when interacting with details in the outer layer?
Give us a concrete example from something like your UI or an interaction with a database.

## Design Patterns (not completed)

Has your group used design patterns in appropriate places in the code? Identified and described any patterns that could be applied in future with more time?
Have you clearly indicated where the pattern was used and possibly pointed out which Pull Request it was implemented in?
Be careful that there aren't any obvious places a design pattern should have been applied that your group forgot to mention.

## Use of GitHub Features

Our group utilised various features of Github to improve our efficiency and keep ourselves organized. 
We set up different **branches** when developing various features for our program. When a feature has been developed, 
a **pull request** will be made. We often have at least two reviewers to review the pull request, 
and more if it is a larger pull request. 

Also, we have taken note of various problems that we have to fix in the future by opening new **issues**. 

To better visualize our progress, we used **Projects** to keep track of our tasks. For each feature that we 
plan to develop, we created a “column” for it and added cards to the column to break down what we have to do, 
or to indicate things to take note of when developing that feature.

## Code Style and Documentation

We ensured that code warnings are resolved and documented most of the methods, especially those that are 
not clear on what they do at first glance. We have also documented some classes that we thought needed 
some explanation of its responsibilities. 

## Testing (not completed)

Are most components of your system tested? Are there any components that would be difficult to test due to your design?
We know time is tight in the project, so it is fine if you don't test everything if your group decides to focus on other aspects of the program, but we want to see evidence of testing.
A significant portion of your code should be tested to earn full marks for this (run your tests with coverage to check).

## Refactoring (not completed)

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

## Functionality (not completed)

Does your program do what the specification says it should do?
Demo your program's functionality to your TA or make a short video!
Is the functionality sufficiently ambitious, given the size of your group?
Can your program store state and load state? I.e. Can the state persist across runs of your program?

