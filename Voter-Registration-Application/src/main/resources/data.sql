insert into user (USER_ID, USERNAME,EMAIL,PASSWORD) values
(1001, 'dhjain','dj2@gmail.com', 'asdfg'),
(1002, 'rjJain','rj2@gmail.com', 'zxcvb'),
(1003, 'nkjain','nk2@gmail.com', 'lkjhg'),
(1004, 'rmjain','rm2@gmail.com', 'poiuy'),
(1005, 'dujain','du2@gmail.com', 'qazxs'),
(1006, 'sdjain','sd2@gmail.com', 'plmnji');

insert into Role (ROLE_ID, ROLE_NAME) values
(101, 'ADMIN'),
(102, 'ELECTION_OFFICIER'),
(103, 'VOTER');

insert into user_role (USER_ID, ROLE_ID) values
(1001,101),
(1002,102),
(1003,103),
(1004,103),
(1005,103),
(1006,103);


insert into constituency (CONSTITUENCY_ID, CONSTITUENCY_NAME) values
(10001, 'Dhule'),
(10002, 'Shirpur'),
(10003, 'Sakri'),
(10004, 'Shindkheda');

insert into election (ELECTION_ID,ELECATION_NAME,START_DATE,END_DATE) values
(100001, 'Local Ward Election', '2023-12-05', '2023-12-06'),
(100002, 'Lok Sabha Election', '2024-12-05', '2024-12-06'),
(100003, 'Rajya Sabha Election', '2024-06-05', '2024-06-06');

insert into voter (VOTER_ID, VOTER_NAME,DATE_OF_BIRTH,CITY,REGISTRATION_STATUS,CONSTITUENCY_ID,USER_ID) values
(2001, 'Dhananjay Jain', '1997-12-03','Dhule',true,10001,1001),
(2002, 'Ros Jain', '1998-02-21','Shirpur',true,10002,1002),
(2003, 'Nik Jain', '2001-08-29','Sakri',true,10003,1003),
(2004, 'Ram Jain', '1997-12-03','Shindkheda',true,10004,1004),
(2005, 'Daya Jain', '1997-12-03','Shindkheda',true,10004,1005),
(2006, 'Sidu Jain', '1997-12-03','Shindkheda',true,10004,1006);

insert into eligible_elections (VOTER_ID,ELECTION_ID) values
(2001,100001),
(2002,100001),
(2003,100002),
(2004,100002),
(2005,100003),
(2006,100003);



