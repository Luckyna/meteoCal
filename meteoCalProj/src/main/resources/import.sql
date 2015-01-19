#import.sql file
-- disabilito i controlli sulle foreign key per cancellare senza problemi le tabelle

SET foreign_key_checks = 0; 

DELETE FROM USER;
DELETE FROM EVENT_IN_CALENDAR;
DELETE FROM INVITATION;
DELETE FROM NOTIFICATION;
DELETE FROM PUBLIC_EVENT;
DELETE FROM PRIVATE_EVENT;
DELETE FROM CALENDAR;
DELETE FROM EVENT;
DELETE FROM PUBLIC_JOIN;
DELETE FROM WEATHER_FORECAST;

-- e poi li riabilito
SET foreign_key_checks = 1;


insert into USER (ID,EMAIL,NAME,PASSWORD,SURNAME,GENDER)
	values
	(1,"a@a","a","a","a",NULL),
	(2,"b@b","b","b","b",NULL),
	(3,"c@c","c","c","c",NULL),
	(4,"umberto.difabrizio@gmail.com","umbo","umbo","difa","M"),
	(5,"v.ceriani92@gmail.com","vale","vale","cer","F"),
	(6,"angelo.francesco.mobile@gmail.com","fra","fra","ang","M"),
	(7,"admin@admin","admin","admin","admin", NULL);

insert into CALENDAR (TITLE, ISDEFAULT, ISPUBLIC, OWNER_ID) 
    values 
    ("Deafult", 1, 0, 1),
    ("Deafult", 1, 0, 2),
    ("Deafult", 1, 0, 3),
    ("Deafult", 1, 0, 4),
    ("Deafult", 1, 0, 5),
    ("Deafult", 1, 0, 6),
    ("Deafult", 1, 0, 7),
    ("Public_Cal", 0, 1, 1),
    ("Public_Cal", 0, 1, 2),
    ("Public_Cal", 0, 1, 3),
    ("Public_Cal", 0, 1, 4),
    ("Public_Cal", 0, 1, 5),
    ("Public_Cal", 0, 1, 6),
    ("Public_Cal", 0, 1, 7);


insert into EVENT (ID, DESCRIPTION, ENDDATETIME, ISOUTDOOR, LOCATION, STARTDATETIME, TITLE, OWNER_ID, TYPE)
	values
	(1, "Evento Privato di amdin", '2014-12-22 01:30', 1 , "a casa mia", '2014-12-22 00:30', "Private Event of admin", 7 , "PRIVATE"),
	(2, "Evento Privato di fra", '2015-01-02 13:00', 1 , "a casa mia", '2015-01-02 12:00', "Private Event of fra", 6, "PRIVATE"),
	(3, "Evento Privato di vale", '2015-01-04 13:00', 1 , "a casa mia", '2015-01-04 12:00', "Private Event of vale", 5, "PRIVATE"),
	(4, "Evento Privato di umbo", '2015-02-04 15:00', 1 , "a casa mia", '2015-02-02 13:00', "Private Event of umbo", 4, "PRIVATE"),
	(5, "Evento Privato di c", '2015-01-02 13:00', 1 , "a casa mia", '2015-01-02 12:00', "Private Event of c", 3, "PRIVATE"),
	(6, "Evento Privato di b", '2015-01-03 11:00', 1 , "a casa mia", '2015-01-03 09:30', "Private Event of b", 2, "PRIVATE"),
	(7, "Evento Privato di a", '2015-01-04 22:00', 1 , "a casa mia", '2015-01-04 20:00', "Private Event of a", 1, "PRIVATE");

insert into PRIVATE_EVENT (ID)
values
(1),(2),(3),(4),(5),(6),(7);

insert into EVENT (ID, DESCRIPTION, ENDDATETIME, ISOUTDOOR, LOCATION, STARTDATETIME, TITLE, OWNER_ID, TYPE)
	values
	(8, "Evento Pubblico di a", '2015-02-22 01:30', 1 , "a casa mia", '2015-02-22 00:30', "Public Event of a", 1, "PUBLIC"),
	(9, "Evento Pubblico di b", '2015-02-02 13:00', 1 , "a casa mia", '2015-02-02 12:00', "Public Event of b", 2,  "PUBLIC"),
	(10, "Evento Pubblico di c", '2015-02-04 13:00', 1 , "a casa mia", '2015-02-04 12:00', "Public Event of c", 3,  "PUBLIC"),
	(11, "Evento Pubblico di umbo", '2015-02-04 15:00', 1 , "a casa mia", '2015-02-02 13:00', "Public Event of umbo", 4,"PUBLIC"),
	(12, "Evento Pubblico di vale", '2015-02-02 13:00', 1 , "a casa mia", '2015-02-02 12:00', "Public Event of vale", 5, "PUBLIC"),
	(13, "Evento Pubblico di fra", '2015-02-03 11:00', 1 , "a casa mia", '2015-02-03 09:30', "Public Event of fra", 6, "PUBLIC"),
	(14, "Evento Pubblico di admin", '2015-02-04 22:00', 1 , "a casa mia", '2015-02-04 20:00', "Public Event of admin", 7,  "PUBLIC");

insert into PUBLIC_EVENT (ID)
values
(8),(9),(10),(11),(12),(13),(14);

insert into EVENT_IN_CALENDAR (eventsInCalendar_ID, TITLE, OWNER_ID)
	values
	(1, "Deafult", 7),
	(2, "Deafult", 6),
	(3, "Deafult", 5),
	(4, "Deafult", 4),
	(5, "Deafult", 3),
	(6, "Deafult", 2),
	(7, "Deafult", 1),
	(8, "Public_Cal", 1),
	(9, "Public_Cal", 2),
	(10, "Public_Cal", 3),
	(11, "Public_Cal", 4),
	(12, "Public_Cal", 5),
	(12, "Public_Cal", 6),
	(14, "Public_Cal", 7);

insert into INVITATION (INVITEE_ID, EVENT_ID, ANSWER)
        values
        (2,8,"YES"),
        (4,8,"YES"),
        (5,8,"YES");


UPDATE SEQUENCE
SET SEQ_COUNT = 14
WHERE SEQ_NAME = 'EVENT_SEQ';

UPDATE SEQUENCE
SET SEQ_COUNT = 7
WHERE SEQ_NAME = 'USER_SEQ';

      