## Progress Report

### Specification
This application help users manage their time and schedule by providing
two main functionalities: a todo-list to store all the tasks and a calender 
to store all events. The application also enables auto-scheduling of tasks
an events to help users organize their time.

### CRC model

### Scenario Walk-through

### What each member has been working on and future plans

### What has worked well so far with our design

### What we are struggling with

Our group had difficulty with the instantiation of entities and their storage in 
memory. We do not have a database for our program, and we had trouble deciding a clean way of 
creating a Calendar object that is accessible to multiple use cases.
We wanted to avoid storing instances of entities in Controllers, since 
that would make the Controller dependent on the entity, thereby
violating the dependency rule. We also did not want individual use cases
storing copies of the same entity, as to avoid duplication. Robert Martin 
outlines a potential solution for clean instantiation of entities
on page 101 of Clean Architecture. The solution involves a factory that creates 
and returns instantiated entities. Our main method could interact with the factory
and these entities via interfaces. However, our skeleton program only involves 
one calender and todo list and we decided that the implementation of a calendar 
factory was unnecessary for our skeleton project. Ultimately We decided to store 
our calendar and todolist in a single use case, that other use cases can access. 
This method is likely to change, especially once we begin storing data in the 
data layer of our application.