create table category(
	id serial PRIMARY KEY,
	category text
);

create table state(
	id serial PRIMARY KEY,
	state text
);

create table roles(
	id serial PRIMARY KEY,
	role_name text
);

create table users(
	id serial PRIMARY KEY,
	name text,
	role_id int references roles(id)
);

create table rules(
	id serial PRIMARY KEY,
	name text
);

create table role_rules(
	id serial PRIMARY KEY,
	role_id int references roles(id),
	rule_id int references rules(id)
);


create table item(
	id serial PRIMARY KEY,
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments(
	id serial PRIMARY KEY,
	comment text,
	item_id int references item(id)
);

create table attachs(
	id serial PRIMARY KEY,
	attachs text,
	item_id int references item(id)
);
