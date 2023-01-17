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
select 
  mems.firstname as memfname, 
  mems.surname as memsname, 
  recs.firstname as recfname, 
  recs.surname as recsname 
from 
  cd.members mems 
  left outer join cd.members recs on recs.memid = mems.recommendedby 
order by 
  memsname, 
  memfname;
````

````sql
select 
  distinct recs.firstname as firstname, 
  recs.surname as surname 
from 
  cd.members mems 
  inner join cd.members recs on recs.memid = mems.recommendedby 
order by 
  surname, 
  firstname;
````

````sql
select 
  distinct mems.firstname || ' ' || mems.surname as member, 
  (
    select 
      recs.firstname || ' ' || recs.surname as recommender 
    from 
      cd.members recs 
    where 
      recs.memid = mems.recommendedby
  ) 
from 
  cd.members mems 
order by 
  member;
````

````sql
select 
  recommendedby, 
  count(*) 
from 
  cd.members 
where 
  recommendedby is not null 
group by 
  recommendedby 
order by 
  recommendedby;
````

````sql
select 
  facid, 
  sum(slots) as "Total Slots" 
from 
  cd.bookings 
group by 
  facid 
order by 
  facid;
````

````sql
select 
  facid, 
  sum(slots) as "Total Slots" 
from 
  cd.bookings 
where 
  starttime >= '2012-09-01' 
  and starttime < '2012-10-01' 
group by 
  facid 
order by 
  sum(slots);

````

````sql
select 
  facid, 
  extract(
    month 
    from 
      starttime
  ) as month, 
  sum(slots) as "Total Slots" 
from 
  cd.bookings 
where 
  extract(
    year 
    from 
      starttime
  ) = 2012 
group by 
  facid, 
  month 
order by 
  facid, 
  month;
````

````sql
select 
  count(distinct memid) 
from 
  cd.bookings
````

````sql
select 
  mems.surname, 
  mems.firstname, 
  mems.memid, 
  min(bks.starttime) as starttime 
from 
  cd.bookings bks 
  inner join cd.members mems on mems.memid = bks.memid 
where 
  starttime >= '2012-09-01' 
group by 
  mems.surname, 
  mems.firstname, 
  mems.memid 
order by 
  mems.memid;
````

````sql
select 
  (
    select 
      count(*) 
    from 
      cd.members
  ) as count, 
  firstname, 
  surname 
from 
  cd.members 
order by 
  joindate
````

````sql
select 
  row_number() over(
    order by 
      joindate
  ), 
  firstname, 
  surname 
from 
  cd.members 
order by 
  joindate
````

````sql
select 
  facid, 
  total 
from 
  (
    select 
      facid, 
      sum(slots) total, 
      rank() over (
        order by 
          sum(slots) desc
      ) rank 
    from 
      cd.bookings 
    group by 
      facid
  ) as ranked 
where 
  rank = 1
````

````sql
select 
  surname || ', ' || firstname as name 
from 
  cd.members
````

````sql
select 
  * 
from 
  cd.facilities 
where 
  upper(name) like 'TENNIS%';
````

````sql
select 
  memid, 
  telephone 
from 
  cd.members 
where 
  telephone ~ '[()]';
````

````sql
select substr (mems.surname,1,1) as letter, count(*) as count 
    from cd.members mems
    group by letter
    order by letter
````