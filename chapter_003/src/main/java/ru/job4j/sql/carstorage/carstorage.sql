--запросы по заданию

--1. Вывести список всех машин и все привязанные к ним детали.
--inner join
select c.name,cb.name,e.name,t.name from car as c
inner join carbody as cb on c.carbody_id=cb.id
inner join engine as e on c.engine_id=e.id
inner join transmission as t on c.transmission_id=t.id;

--left outer join
select c.name,cb.name,e.name,t.name from car as c
left outer join carbody as cb on c.carbody_id=cb.id
left outer join engine as e on c.engine_id=e.id
left outer join transmission as t on c.transmission_id=t.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
--2.1 кузов
select cb.name from carbody as cb left outer join car as c on c.carbody_id=cb.id where c.id is null;

--2.2 двигатель
select e.name from engine as e left outer join car as c on c.engine_id=e.id where c.id is null;

--2.3 коробка передач
select t.name from transmission as t left outer join car as c on c.transmission_id=t.id where c.id is null;