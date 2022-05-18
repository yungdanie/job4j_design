
select product.name from product where type_id = (select id from type where type.name = 'СЫР');

select product.name from product where product.name ilike '%мороженое%';

select product.name from product where current_date > expired_date;

select max(product.price) from product;

select s.name, count(*) from product join type as s on s.id = product.type_id
group by s.id;

select product.name from product where type_id = (select id from type where type.name = 'СЫР') OR type_id = (select id from type where type.name = 'МОЛОКО');

select s.name, count(*) from product join type as s on s.id = product.type_id
group by s.id
having count(*) < 10;

select s.name, product.name from product join type as s on s.id = product.type_id;


