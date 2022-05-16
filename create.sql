create table users(
	id serial PRIMARY KEY,
	name text,
	role_id references roles(id)
);

create table roles(
	id serial PRIMARY KEY,
	role_name text
);

create table rules(
	id serial PRIMARY KEY,
	name text
);

create table role_rules(
	id serial PRIMARY KEY,
	role_id references roles(id),
	rule_id references rules(id)
);


create table item(
	id serial PRIMARY KEY,
	user_id references users(id),
	category_id references category(id)
	state_id references state(id)
);

create table comments(
	id serial PRIMARY KEY,
	comment text,
	item_id references item(id)
);

create table attachs(
	id serial PRIMARY KEY,
	attachs text,
	item_id references item(id)
);

create table category(
	id serial PRIMARY KEY,
	category text
);

create table state(
	id serial PRIMARY KEY,
	state text
);