select * from departments d left join employees e on e.department_id = d.id;

select * from departments d right join employees e on e.department_id = d.id;

select * from departments d full join employees e on e.department_id = d.id;





select * from departments d left join employees e on e.department_id = d.id where e.department_id is null;




select * from departments d right join employees e on e.department_id = d.id where d.id is not null;
select * from departments d left join employees e on e.department_id = d.id where e.department_id is not null;


select t1.gender as Пол, t2.name as Имя from teens t1 cross join teens t2;
