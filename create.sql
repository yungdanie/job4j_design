create table cats (
	id serial PRIMARY KEY,
	name text,
	age integer,
	birthday date
);
insert into cats (name, age, birthday) values ('пуся', 17, '1998-01-08'),
									('котя', 5, '1995-03-13'),
									('мотя', 10, '2001-10-25');
update cats set name = 'персик' WHERE (cats.age = 17);
delete from cats;
select * from cats;