create table departments(
	id serial primary key,
	name text
);

create table employees(
	id serial primary key,
	name text,
	department_id int references departments(id)
);

insert into departments(name) values ('Отдел 1'), ('Отдел 2'), ('Отдел 3');
insert into employees(name, department_id) values ('Игорь', 1), ('Вадим', 1), ('Вера', 2), ('Олег', 3), ('Имя', 3), ('Оля', 3);
insert into employees(name) values ('Даниил'), ('Стас');


create table teens(
	id serial primary key,
	name text,
	gender text
);

insert into teens(name, gender) values ('Ваня', 'муж'), ('Саша', 'жен'), ('Миша', 'муж');