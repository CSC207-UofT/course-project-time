## Scenario Walk-through

When the application starts running, an empty Calendar is created 
with no events. The user chooses an action from the following:
* View all events
* View all tasks
* Create a new task
* Auto-schedule a task

If the user chooses to view all events or tasks, the list of events or tasks
will be printed on the console, along with some of their details.

If the user chooses to create a new task, the user will be prompted 
to input the task name, duration needed, deadline, and a list of sub-tasks.

If the user chooses to auto-schedule a task, the user will be prompted
to choose a task from the list of all tasks. Then, the program will suggest an 
available time to the user and asks for the user's confirmation. If the suggested time
is rejected by the user, the program will suggest a new time until confirmation.