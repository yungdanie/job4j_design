create table body(
	id serial primary key,
	name text
);

create table engine(
	id serial primary key,
	name text
);

create table transmission(
	id serial primary key,
	name text					  
);

create table detail(
	id serial primary key,
	name text					  
);

create table car(
	id serial primary key,
	name text,
	body_id int references body(id),
	engine_id int references engine(id),
	trans_id int references transmission(id),
	detail_id int references detail(id)
);

insert into body(name) values ('открытый'), ('закрытый'), ('ненужный');
insert into engine(name) values ('мощный'), ('слабый'), ('средний');
insert into transmission(name) values ('полный привод'), ('задний привод');
insert into detail(name) values ('ненужная деталь');


insert into car(name, body_id, engine_id, trans_id) values ('крутая машина 1', 1, 1, 1), ('крутая машина 1', 1, 2, 2);
insert into car(name, body_id) values ('машина с кузовом 1', 1), ('машина с кузовом 2', 2);
insert into car(name, body_id, engine_id) values ('машина с кузовом и двигателем 1', 1, 2), ('машина с кузовом и двигателем 2', 2, 2);