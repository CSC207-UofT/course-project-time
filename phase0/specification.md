## Specification

This application helps users manage their time and schedule.
There is a **todo-list** which stores all the **tasks** that the user wants to
complete. Each task stores the task name, deadline, the duration needed for the task, along with a number of sub-tasks.
The todo-list allows the user to indicate if the tasks are completed.

Another function that the application has is a **calendar** that manages the schedule of the user. 
The calendar stores all **events**, where events store a specific time of
occurrence, along with the associated task. The user would be able to indicate if the event is 
one-time or repeated.

The application helps the user to auto-schedule a task into an event.
Given a task in the todo-list, the application will find a suitable time
from the calendar and suggest it to the user. Upon confirmation with the user, 
an event will be scheduled for the user to complete the task.

The user will also be able to reschedule an event if they did not end up completing 
the event in the scheduled time. 

The application will also contain a notification system that will notify users when their events are about to begin 
so that the users do not miss their events.
