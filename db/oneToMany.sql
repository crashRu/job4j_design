create table emploee(
e_id int PRIMARY KEY,
e_name text,
e_age int
);

create table disabled_employee(
de_id int PRIMARY KEY,
e_id int  references emploee(e_id)
);