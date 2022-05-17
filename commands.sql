select product.name from product where type_id = 1;
select product.name from product where type_id = (select id from type where type.name = 'СЫР');


select product.name from product where product.name ilike '%мороженое%';

select product.name from product where current_date > expired_date;


