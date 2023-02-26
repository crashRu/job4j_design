create table user_data (
    id serial primary key, 
    name text,
	password varchar(255),
	age smallint,
	salary money
);
insert into user_data(name, password, age, salary) values ('Kirill','passwd','27','2000');
insert into user_data(name, password, age, salary) values ('Вася','passwd','37','6000');
update user_data set age = 39 WHERE age > 30;
select * from user_data; 
delete from user_data;
select * from user_data;