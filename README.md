# TimeStampsTool
Time In Out with User Login and Admin Area for editings and Stats

its demo pics without the sql db , i got it on the other pc. so it looks empty , the dropdowns aswell filled by the db

![image](https://user-images.githubusercontent.com/105649203/202903091-9561d9ff-1594-432f-86b3-a886e675d853.png)

![image](https://user-images.githubusercontent.com/105649203/202903542-0ec72337-c4b3-4170-9da9-d0124a1ef03c.png)

![image](https://user-images.githubusercontent.com/105649203/202903377-ea42ecbb-3663-456d-af1c-be38871c7488.png)
![image](https://user-images.githubusercontent.com/105649203/202903410-fdb4ff40-c5f1-41da-a6e0-21b8af404032.png)
![image](https://user-images.githubusercontent.com/105649203/202903463-32023e59-82cc-42a8-ac5b-40e29c67783e.png)


well its a program for saving in and out timestamps in an sql database for multiple users and comes with a admin area for maintence and stats to pull. 

as mentioned the code is not beautiful, it means the actionlisteners and sql statements are in the Gui.class file all together under the Labels and Fields. 

user can login and then choose from a dropdown menu wich task he want to clock in or out for. he has a display area where he sees for example the login dates
or infos like " already logged in at so on " . .   
so thats it for a normal user

But, if the user has a 1 in the admin column in the sql db. he will be able to go to the admin area inside the app wich normal users cant.
for him or her a button will be visible that says "Admin".
in that section they are able to add users and passwords into the sql db, to see who is logged in and pull other stats. 

also in this area the dropdown menu can be edited. its possible to add and remove tasks for the users to choose. 

also edit the users registrations and set the adminrights or edit data.

that is the current stage. 

now I could beautifuly build in several more options to have additional dynamic admin tools in it and make it as wished for the enduser. 
Or building in a Edit Area where the Admin can create himself new buttons and pages for example. or set his own sql pull statement. so building a low code section )

its a 2-3 days project so far actualy. Its the 4th app i developed. after this came the garage simulation. 


