insert into devices(name, price) values ('Клавиатура', 6000), ('Мышка', 5000), ('Наушники', 10000), ('Кресло', 15000);
insert into people(name) values ('Игорь'), ('Махмед'), ('Абдуль');
insert into devices_people(people_id, device_id) values (1, 1), (1, 2), (1, 3), (1, 4),
														(2, 1), (2, 4),
														(3, 3), (3, 4);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people as dp
join devices as d
on device_id = d.id
join people as p
on people_id = p.id
group by p.name;

select p.name, avg(d.price) from devices_people as dp
join devices as d
on device_id = d.id
join people as p
on people_id = p.id
group by p.name
having avg(d.price) > 5000;