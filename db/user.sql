CREATE TABLE rules (
rules_id serial PRIMARY KEY,
rules_name text,
level_role int
);

CREATE TABLE role(
role_id serial PRIMARY KEY,
role_name text
);

CREATE TABLE users (
user_id int PRIMARY KEY,
user_name text,
user_password text,
email text,
user_role text,
role_id int references role(role_id)
);

CREATE TABLE role_rules(
id serial PRIMARY KEY,
role_id int REFERENCES role(role_id),
rules_id int  REFERENCES rules(rules_id)
);

CREATE TABLE state(
state_id serial PRIMARY KEY,
status text
);

CREATE TABLE category(
category_id serial PRIMARY KEY,
category_name text
);

CREATE TABLE item(
item_id serial PRIMARY KEY,
item_name text,
item_text text,
user_id serial references users(user_id),
category_id serial references category(category_id),
state_id serial references state(state_id)
);

CREATE TABLE attachs(
attachs_id serial PRIMARY KEY,
attachs_name text,
attachs_size int,
attachs_link text,
item_id int references item(item_id)
);

CREATE TABLE comments(
comments_id serial PRIMARY KEY,
comments_text text,
item_id int references item(item_id)
);

