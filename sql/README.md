###### Question 1: Show all members 

```sql
SELECT * FROM cd.members;
```

###### Exercise 1
`````sql
CREATE TABLE cd.facilities
(
    facid integer NOT NULL,
    name character varying(100) NOT NULL,
    membercost numeric NOT NULL,
    guestcost numeric NOT NULL,
    inititaloutlay numeric NOT NULL,
    PRIMARY KEY (facid)
);

CREATE TABLE cd.bookings
(
    facid integer NOT NULL,
    memid integer NOT NULL,
    starttime timestamp NOT NULL,
    slots integer NOT NULL.
    PRIMARY KEY (facid),
    FOREIGN KEY (memid) REFERENCE cd.members (memid),
    FOREIGN KEY (facid) REFERENCE cd.facilities (facid)    
);

CREATE TABLE cd.members
(
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

