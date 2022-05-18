select c.name, b.name, e.name, t.name from car c left join body b on c.body_id = b.id
left join engine e on c.engine_id = e.id
left join transmission t on c.trans_id = t.id;

select d.name from detail d left join car c on d.id = c.detail_id where c.detail_id is null;

select d.name from body d left join car c on d.id = c.body_id where c.body_id is null;

select e.name from engine e left join car c on e.id = c.engine_id where c.engine_id is null;

select t.name from transmission t left join car c on t.id = c.trans_id where c.trans_id is null;