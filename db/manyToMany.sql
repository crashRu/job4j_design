CREATE TABLE courses(
	c_no text PRIMARY KEY,
	title text,
	hours int
);

CREATE TABLE students(
s_id int PRIMARY KEY,
name text,
start_year int
);

CREATE TABLE courses_students(
cs_id int PRIMARY KEY,
s_id int REFERENSES students(s_id),
c_no text REFERENSES courses(c_no),
);