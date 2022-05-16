create table plane(
	id serial PRIMARY KEY,
	name text,
	version integer
);

create table flight(
	id serial PRIMARY KEY,
	flight_num integer,
	plane_id references plane(id) unique
);

create table passenger(
	id serial PRIMARY KEY,
	first_name text,
	second_name text,
	flight_dest_id references flight(id)
);


create table connecting_flight(
	id serial PRIMARY KEY,
	passenger_id references passenger(id),
	flight_id references flight(id)
);

	
	