###### Question 1: Show all members 

```sql
SELECT * FROM cd.members;
```

###### Exercise 1
`````sql
CREATE TABLE cd.facilities (
  facid integer NOT NULL, 
  name character varying(100) NOT NULL, 
  membercost numeric NOT NULL, 
  guestcost numeric NOT NULL, 
  inititaloutlay numeric NOT NULL, 
  PRIMARY KEY (facid)
);

CREATE TABLE cd.bookings (
  facid integer NOT NULL, 
  memid integer NOT NULL, 
  starttime timestamp NOT NULL, 
  slots integer NOT NULL.PRIMARY KEY (facid), 
  FOREIGN KEY (memid) REFERENCE cd.members (memid), 
  FOREIGN KEY (facid) REFERENCE cd.facilities (facid)
);

CREATE TABLE cd.members (
  memid integer NOT NULL, 
  surname character varying(200) NOT NULL, 
  firstname character varying(200) NOT NULL, 
  address character varying(300) NOT NULL, 
  zipcode integer varying(20) NOT NULL, 
  telephone character varying(20) NOT NULL, 
  recommendedby integer NOT NULL, 
  joindate timestamp NOT NULL, 
  PRIMARY KEY (memid), 
  FOREIGN KEY (recommendedby) REFERENCE cd.members (memid), 
  );

`````

###### Exercise 2

Done on JRD machine and local WSL2.

###### Practice requests
````sql
insert into cd.facilities (
  facid, name, membercost, guestcost, 
  initialoutlay, monthlymaintenance
) 
values 
  (9, 'Spa', 20, 30, 100000, 800);

````

````sql
insert into cd.facilities (
  facid, name, membercost, guestcost, 
  intitialoutlay, monthlymaintenance
) 
select 
  (
    select 
      max(facid) 
    from 
      cd.facilities
  )+ 1, 
  "Spa", 
  20, 
  30, 
  100000, 
  800;
````

````sql
update 
  cd.facilities 
set 
  initialoutlay = 100000 
where 
  facid = 1;
````

````sql
update 
  cd.facilities 
set 
  membercost = (
    select 
      membercost * 1.1 
    from 
      cd.facilities 
    where 
      facid = 0
  ), 
  guestcost = (
    select 
      guestcost * 1.1 
    from 
      cd.facilities 
    where 
      facid = 0
  ) 
where 
  facid = 1;
````

````sql
delete from 
  cd.bookings;
````

````sql
delete from 
  cd.members 
where 
  memid = 37;
````

````sql
select 
  distinct surname 
from 
  cd.members 
union 
select 
  distinct name 
from 
  cd.facilities;
````

````sql
select 
  cd.bookings.starttime 
from 
  cd.bookings 
  inner join cd.members on cd.bookings.memid = cd.members.memid 
where 
  cd.members.surname = 'Farrell' 
  and cd.members.firstname = 'David';
````

````sql

````
