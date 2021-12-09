## Specification

This is a time management application that helps users manage their schedule, to-dos, and provides a pomodoro interface
to help users better use their time.

There is a todo-list which stores all the tasks that the user wants to
complete. Each task stores the task name, deadline, the duration needed for the task, along with a number of sub-tasks.
The todo-list allows the user to indicate if the tasks are completed.

Another function that the application has is a calendar that manages the schedule of the user.
The calendar stores all events, where events store a specific time of
occurrence, along with the associated task. The user would be able to indicate if the event is
one-time or repeated.

The application helps the user to auto-schedule a task into an event.
Given a task in the todo-list, the application will find a suitable time
from the calendar and suggest it to the user. Upon confirmation with the user,
an event will be scheduled for the user to complete the task.

The user will also be able to reschedule an event if they did not end up completing
the event in the scheduled time, or if they want to change a time for the event.

Furthermore, the application contains a notification system that notifies users when their events are about to begin
so that the users do not miss their events, and when the tasks will be due. Users
will be able to set how long before the deadline should the notification be sent. Users
can choose to be notified through desktop or email, or both.

Lastly, there is a pomodoro feature that helps users work productively.
The user will be able to select durations for "work time", and a "break time".
Our program will then start timing and alternate between "work time" and "break time"
for the user to work and take a break accordingly.


In order to help users better use our application, a desktop application has been developed.
The home page displays to the user their scheduled events for the day and any tasks that have a deadline today.
The calendar page, with monthly and weekly view options, displays all events.
The todo-list page shows the tasks, uncompleted and completed. Users can add tasks from there, and 
by clicking into any tasks, the task details will be showed, allowing users to edit the task as well.
The pomodoro page provides feature to help users work productively. 
It allows users to input a work time and break time which the program alternates between
The user is able to leave the pomodoro page without the timer stopping.
Lastly, the settings page which enables the user to edit settings related to the notification and choose
to export data as an ICS file.
