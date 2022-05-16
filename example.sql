create table account(
	id serial primary key,
	num int
);

create table users(
	id serial primary key,
	name text,
	account_id int references account(id)
);											


insert into account(num) values (1111),
								(2222);
insert into users(name, account_id) values ('Vasya', 1),
											('Petya', 2);

select us.name as Имя
from users as us
join account as acc
on us.account_id = acc.id;


select num as Номер
from users as us
join account as acc
on us.account_id = acc.id;


select acc.num as Номер, us.name as Имя 
from users as us
join account as acc
on us.account_id = acc.id;
