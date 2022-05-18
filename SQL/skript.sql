select * from departments d left join employees e on e.department_id = d.id;

select * from departments d right join employees e on e.department_id = d.id;

select * from departments d full join employees e on e.department_id = d.id;





select * from departments d left join employees e on e.department_id = d.id where e.department_id is null;




select * from departments d right join employees e on e.department_id = d.id where d.id is not null;
select * from departments d left join employees e on e.department_id = d.id where e.department_id is not null;


select t1.gender as Пол, t2.name, t2.gender, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;
