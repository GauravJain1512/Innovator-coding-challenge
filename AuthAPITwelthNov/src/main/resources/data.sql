insert into user (USER_ID, USERNAME, EMAIL, PASSWORD) values
(10001, 'dhjain','jn2@gmail.com','asd'),
(10002, 'ghjain','gn2@gmail.com','zxc');

insert into role(ROLE_ID, ROLE_NAME) values
(101, 'ADMIN'),
(102, 'NORMAL');

insert into user_role(USER_ID, ROLE_ID) values
(10001,101),
(10002,102);