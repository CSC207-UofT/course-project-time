# Accessibility Design Document

## Design principles

### Equitable Use

We tried to implement accessible text attributes onto important elements of our GUI. 
Accessible text can be used by screen readers to make the usage of our application easier 
for the visually impaired, thereby not segregating them from the usage of our program. 
Unfortunately, we did not have time to add accessible text to more complicated elements 
of the GUI, like the calendar and the todolist. In the future, we could write a custom parser, 
which could convert the entries in a calendar and todo list into a readable string. 
Then, the accessible text attribute of these GUI elements could be set to the readable string.

### Flexibility In Use

The option to export users data to an ICS file appeals to the flexibility in use principle. 
ICS is an almost universal format for storing calendar data and having the option to export 
in this format gives users the option to use the data from our program in whichever way they prefer. 
Our program also offers the ability to view events in both a monthly and weekly format, 
thereby supporting multiple preferences a user might have.

### Simple and Intuitive Use

Our usage of the CalendarFX dependency has made the usage of our calendar system intuitive. 
Information about events can be viewed by clicking on them, which feels very natural. 
Furthermore, by only showing information about an event when it is clicked, we hide irrelevant information from the user. 
One way we could adhere to this principle more in the future is by adding an explanation of 
what a pomodoro productivity timer is and how to use it, since users may find the current implementation confusing.

### Perceptible Information

We created all of our GUI elements off of a basic form, in order to keep the design of specific elements consistent. 
Essential elements of our GUI are made larger than the less essential elements, to differentiate them. 
However, the perceptibility of our program could be improved by standardizing our fonts and buttons. 
For example, we could make all save buttons purple, and make all titles use the same font, 
to distinguish interface elements, based on their functionality.

### Tolerance for Error

Our program has checks in place to notify the user when data is entered incorrectly. 
For example, if the time entered into the pomodoro timer is not a valid time, the program will notify the user, 
and then use a default time. Additionally, on our todo list, we offer the ability to edit tasks after 
they have been created. In doing so, the user can rectify input errors at any time. 

### Low Physical Effort

The small window size of our program means users do not need to move their mouse, or equivalent hardware 
too much to navigate our program. Most actions can be completed with simple key presses and mouse clicks, 
and the user does not need to re-input data very frequently. One way we could reduce the physical effort 
required even further is by enabling the user to confirm inputted textual information by pressing the enter key, 
rather than having to click a button.

### Size and Space for Approach and Use

We do not believe the "Size and Space for Approach and Use" principle is not applicable to our program. 
The "Size and Space for Approach and Use" principle of design focuses on ensuring there are not any physical 
barriers preventing users from interacting with our program. Our program is designed to be interacted with 
via keyboard, mouse or any equivalent assistive technologies. Since we have little control over the work space of 
our users, whether or not a user has adequate space to use our program is largely dependent on them.

## Intended Demographics

## Excluded Demographics

There are a few demographics that would likely not use our program. The visually impaired would likely have a difficult time using our program. The graphical user interface is a big component of our design. The majority of our functionality is small, repeatable events triggered by the UI, for example, adding an event to a calendar, which is then presented in a graphical manner. Being incapable of seeing our UI would prevent the user from making these interactions, and viewing their data in any meaningful way. Additionally, our program offers very little in terms of sound. For instance, there is no audio cue when the timer in our program is completed. Due to the lack of audio support, the visually impaired would have difficulty using the notification based features of our program. Secondly, people that have difficulty focusing on one task may have difficulty using our program. As previously stated, the usage of our program is very meticulous. People with short attention spans would likely find it difficult to input their data into our program. Our program also does not offer very much visual stimulation and thereby does not grab the attention. It is for these reasons that I believe that the visually impaired, and people with short attention spans may have difficulty using our program.
