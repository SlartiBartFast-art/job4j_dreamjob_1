CREATE TABLE candidate (
 id SERIAL PRIMARY KEY,
 name TEXT,
 position TEXT,
 created TIMESTAMP not null,
 city_id int references city(id)
);