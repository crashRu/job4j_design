create TABLE users(
user_id serial PRIMARY KEY,
user_name text,
user_password text,
email text,
user_role text,

create TABLE user_role(
role_id serial PRIMARY KEY,
role_name text,
user_id int references users(user_id)
)

create TABLE t_rules(
rules_id serial PRIMARY KEY,
rules_name text,
level_role int
)

create TABLE role_rules(
id serial PRIMARY KEY,
role_id references user_role(role_id),
rules_id references t_rules(rules_id)
)

create TABLE state(
state_id serial PRIMARY KEY,
status text
)

create TABLE category(
category_id serial PRUMARY KEY,
name text
)

create TABLE attachs(
attachs_id serial PRIMARY KEY,
attachs_name text,
attachs_size int,
attachs_link text
item_id references item(item_id)
)

create TABLE item(
item_id serial PRIMARY KEY,
item_name text,
item_text text,
user_id serial references users(user_id),
category_id serial references category(category_id),
state_id serial references state(state_id)
)

create TABLE comments(
comments_id serial PRIMARY KEY,
comments_text text,
item_id references item(item_id)
)

