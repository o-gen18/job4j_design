--создаю требуемые табицы и ввожу в них данные
create database carstorage;

\c carstorage;

create table engine(
id serial primary key,
name varchar(200));

create table carbody(
id serial primary key,
name varchar(200));

create table transmission(
id serial primary key,
name varchar(200));

create table car(
id serial primary key,
name varchar(200),
carbody_id int references carbody(id),
engine_id int references engine(id),
transmission_id int references transmission(id));

insert into engine(name) values('Diesel 5.0');

insert into engine(name) values('Diesel 3.0');

insert into engine(name) values('Petrol 2.5');

insert into engine(name) values('Petrol 2.0');

insert into engine(name) values('Petrol 1.6');

insert into carbody(name) values('Sedan');

insert into carbody(name) values('Hetchback');

insert into carbody(name) values('Pick-up');

insert into carbody(name) values('Van');

insert into transmission(name) values('Automatic');

insert into transmission(name) values('Manual');

insert into transmission(name) values('Robot');

insert into car(name, carbody_id, engine_id, transmission_id) values ('BMW 5', 1, 3, 1);

insert into car(name, carbody_id, engine_id, transmission_id) values ('Hyundai solaris', 1, 5, 2);