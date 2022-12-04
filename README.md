# simple-events-management-using-jsp-servlets
This uses JSP and Servlets to display simple working of the events management and logging, uses Mysql DB to store the events, its information and the RSVP of users.
This displays a very basic infrastructre. Currently, the users are allowed to RSVP multiple times, no tracking of users is performed.
The structure for the tables used in this is:
user_info: 
username varchar(50) 
password varchar(20)

event_info:
username varchar(50) foreign key
eventname varchar(50) 
date varchar(10) 
time varchar(10) 
location varchar(50) 
description varchar(50) 
yes_count int
no_count int
