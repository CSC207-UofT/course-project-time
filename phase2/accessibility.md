#Accessibility Design Document

##Equitable Use

We tried to implement accessible text attributes onto important elements of our GUI. Accessible text can be used by screen readers to make the usage of our application easier for the visually impaired, thereby not segregating them from the usage of our program. Unfortunately, we did not have time to add accessible text to more complicated elements of the GUI, like the calendar and the todolist. In the future, we could write a custom parser, which could convert the entries in a calendar and todo list into a readable string. Then, the accessible text attribute of these GUI elements could be set to the readable string.

##Flexibility In Use

The option to export users data to an ICS file appeals to the flexibility in use principle. ICS is an almost universal format for storing calendar data and having the option to export in this format gives users the option to use the data from our program in whichever way they prefer. Our program also offers the ability to view events in both a monthly and weekly format, thereby supporting multiple preferences a user might have.

##Simple and Intuitive Use

Our usage of the CalendarFX dependency has made the usage of our calendar system intuitive. Information about events can be viewed by clicking on them, which feels very natural. Furthermore, by only showing information about an event when it is clicked, we hide irrelevant information from the user. One way we could adhere to this principle more in the future is by adding an explanation of what a Pomodoro productivity timer is and how to use it, since users may find the current implementation confusing.

##Perceptible Information

We created all of our GUI elements off of a basic form, in order to keep the design of specific elements consistent. Essential elements of our GUI are made larger than the less essential elements, to differentiate them. However, the perceptibility of our program could be improved by standardizing our fonts and buttons. For example, we could make all save buttons purple, and make all titles use the same font, to distinguish interface elements, based on their functionality.
