# TimeStampsTool
Time In Out with User Login and Admin Area for editings and Stats

its demo pics without the sql db , i got it on the other pc. so it looks empty , the dropdowns aswell filled by the db

![image](https://user-images.githubusercontent.com/105649203/202903091-9561d9ff-1594-432f-86b3-a886e675d853.png)

![image](https://user-images.githubusercontent.com/105649203/202903542-0ec72337-c4b3-4170-9da9-d0124a1ef03c.png)

![image](https://user-images.githubusercontent.com/105649203/202903377-ea42ecbb-3663-456d-af1c-be38871c7488.png)
![image](https://user-images.githubusercontent.com/105649203/202903410-fdb4ff40-c5f1-41da-a6e0-21b8af404032.png)
![image](https://user-images.githubusercontent.com/105649203/202903463-32023e59-82cc-42a8-ac5b-40e29c67783e.png)


well its a program for saving in and out timestamps in an sql database for multiple users and comes with an admin area for maintence and stats to pull. 

as mentioned the code is not beautiful, it means the actionlisteners and sql statements are in the Gui.class file all together under the Labels and Fields. 

so not an easy overview maybe but I can explain shortly

user can login and then choose from a dropdown menu wich task he want to clock in or out for. he has a little display area where he sees for example the login dates
or infos for example " already logged in at so on " . .   
so thats it for a normal user as I remember

But, if the user has a 1 in the admin column in the sql db. he will be able to go to the admin area inside the app wich normal users cant.
for him or her a button will be visible where they can go to the admin area. 
in that area they are able to add users into the sql db, and to see who is logged in and pull stats. so its also a leader area for have an overview of employes logged in on what

also in this area the dropdown menu can be edited. its possible to add and remove tasks like for example lunch break or fieldmission or so. 

also edit the users registrations via the Gui. he can set the Admin option for any other user he wants to be admin. 

so thats the momentary stage. 

now i could beautifuly build in more several options to have dynamic admin tools in it and make it actually perfect for the enduser.

the Gui color world kinda nice very easy kept. its a 2 days project or so actualy. right its the 4th app i developed. after this came the garage simulation i believe 


