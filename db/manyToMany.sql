create table courses(
	c_no text PRIMARY KEY,
	title text,
	hours int
);

create table students(
s_id int PRIMARY KEY,
name text,
start_year int
);

create table exams(
s_id int REFERENSES students(s_id),
c_no text courses(c_no),
score int,
CONSTRAINT pk PRIMARY KEY(s_id, c_no)
);