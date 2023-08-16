This software allows you to make trip reservations. You can:
1) login:
    enter your username and password in the boxes, then hit login, if you are registered. Once logged in, you have access.
2) register:
   enter a username and password, hit register to register, a popup should say you are registered. 
3) delete a trip:
    select a trip to delete in the list, then hit delete. It will disappear from the list. The list shows train name, train number, trip id, and the date
4) add a trip:
    fill out the train number, train name, and date fields in the panel. Hit add trip to add a trip to the list. 
5) logout:
    hit logout to clear everything and log you out.

How it works:
 At the core of the code are two classes, Users and trainInfo. These store the trip and user information. Outside of those, there are collection classes that allow for CRUD of the user and train classes. Alongside these are three tests that run, to test users, trains, and the high level system. The trainAppMaven class has instances of the collection classes, and allows for the login, logout, delete, and add functions. On top of all that is the GUI driver, which has the action handlers for the panel. These handlers call the login, logout, delete, and add methods in the main class when the buttons are pressed. It works down to the train and user classes at the core.  

