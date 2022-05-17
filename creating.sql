create table type(
	id serial primary key,
	name text
);

create table product(
	id serial primary key,
	name text,
	type_id int references type(id),
	expired_date date,
	price int);
	
insert into type(name) values ('СЫР'), ('МОЛОКО');
insert into product(name, type_id, expired_date, price) values ('Сыр моцарела', 1, '2022-05-17', 1000),
																('Сыр французский', 1, '2022-05-22', 2000),
																('Сыр итальянский', 1, '2022-05-25', 3000),
																('Сыр какой-то', 1, '2022-10-01', 7000),
																('Молоко', 2, '2022-05-20', 100),
																('Сырок', 2, '2022-05-24', 30)
																('мороженое', 2, '2022-10-01', 100),
																('вкусное МОРОЖЕНОЕ', 2, '2022-10-01', 100)
																('невкусное мороженое(пропало)', 2, '2021-10-10', 10);