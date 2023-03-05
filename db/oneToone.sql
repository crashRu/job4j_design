create table person(
person_id int PRIMARY KEY NOT NULL,
first_name text,
last_name text,
person_age int
);

create table phone(
phone_id int PRIMARY KEY NOT NULL,
per_id int REFERENCES person(person_id),
phone_number int
);
