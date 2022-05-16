insert into roles(role_name) values ('engineer'),
								('programmer'); 
insert into users(name, role_id) values ('Igor', 1),
										('Nikita', 2);
insert into rules(name) values ('ходить на работу');

insert into role_rules(role_id, rule_id) values (1, 1),
										 		(2, 1);
insert into state(state) values ('state');
insert into category(category) values ('category');
insert into item(user_id, category_id, state_id) values (1, 1, 1);
insert into attachs(attachs, item_id) values ('attachment', 1);
insert into comments(comment, item_id) values ('comment', 1);