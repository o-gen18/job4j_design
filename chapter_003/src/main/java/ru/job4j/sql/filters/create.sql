--скрипты для создания таблиц и их содержимого
create database Filters --создать б\д

\c filters --подключиться к ней

 create table type( --создать таблицу типов
id serial primary key,
name varchar(2000));

insert into type(name) values ('СЫР');

insert into type(name) values ('МОЛОКО');

insert into type(name) values ('МЯСО');

insert into type(name) values ('ВОДА');

insert into type(name) values ('ДЕСЕРТЫ');

select * from type; --проверить что получилось

 create table product( --создать таблицу продуктов
id serial primary key,
name varchar(2000),
type_id int references type(id),
expired_date timestamp not null default now(),
price int,
amount int);

insert into product(name, type_id, expired_date, price, amount)
values ('Мороженое на палочке', '5', '2020-08-08 22:22:22', '105', '32');

insert into product(name, type_id, expired_date, price, amount)
values ('Курица', '3', '2020-08-07 20:21:12', '250', '11');

insert into product(name, type_id, expired_date, price, amount)
values ('Сок яблочный', '4', '2020-10-11 20:21:12', '50', '15');

insert into product(name, type_id, expired_date, price, amount)
values ('Творог', '2', '2020-08-05 08:11:01', '75', '8');

insert into product(name, type_id, expired_date, price, amount)
values ('Молочный напиток', '2', '2020-08-10 08:11:01', '65', '21');

insert into product(name, type_id, expired_date, price, amount)
values ('Молоко коровье', '2', '2020-08-01 17:31:11', '83', '50');

insert into product(name, type_id, expired_date, price, amount)
values ('Сыр плавленый', '1', '2020-09-11 17:31:11', '120', '13');

insert into product(name, type_id, expired_date, price, amount)
values ('Сыр российский', '1', '2020-09-25 17:31:11', '250', '17');