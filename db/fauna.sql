create table fauna
(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values('Змея обыкновенная', 12000, date '1234-05-06');
insert into fauna(name, avg_age, discovery_date) values('Обезьяна', 10, date '1572-05-06');
insert into fauna(name, avg_age, discovery_date) values('Собака', 30, date '1964-07-08');
insert into fauna(name, avg_age, discovery_date) values('кошка', 30, null);
insert into fauna(name, avg_age, discovery_date) values('Testfishtest', 30, date '1234-05-06');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < date '1950-01-01';