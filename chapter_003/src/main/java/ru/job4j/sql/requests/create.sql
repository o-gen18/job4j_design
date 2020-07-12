--создать базу данных "requests"
create database requests;

--создать таблицы для хранения структуры системы заявок
create table users(     --таблица юзеров
id serial primary key,
name varchar(2000));

create table item ( --таблица для заявок
id serial primary key,
item varchar(2000));

create table role (     --таблица для ролей
id serial primary key,
role varchar(2000));

create table rule (     --таблица для прав ролей
id serial primary key,
rule varchar(2000));

create table comments (     --таблица для комментариев
	id serial primary key,
	comment varchar(2000));

create table attach (       --таблица для приложенных файлов(сделал поле-массив байтов, чтобы файл так представить)
id serial primary key,
attach bytea);

create table state (        --таблица состояний заявки
id serial primary key,
state varchar(2000));

create table category (        --таблица для категорий заявки
id serial primary key,
category varchar(2000));

create table rule_to_role (     --вспомогательная таблица для связи прав и ролей (many-to-many)
id serial primary key,
rule_id int references rule(id),
role_id int references role(id));

alter table users add column role_id int references role(id);   --добавить в таблицу юзеров поле role_id для связки с role(many-to-one)

alter table item add column user_id int references users(id);     --добавить в таблицу заявок поле user_id для связки с user(many-to-one)

alter table comments add column item_id int references item(id);    --item-comments(one-to-many)

alter table attach add column item_id int references item(id);  --item-attach(one-to-many)

alter table item add column category_id int references cathegory(id);  --item-category(many-yo-one)

alter table item add column state_id int references state(id);  --item-state(many-to-one)

insert into role(role) values ('admin');

insert into role(role) values ('new user');

insert into role(role) values ('old user');

insert into users(name, role_id) values ('Petr', 1);

insert into users(name, role_id) values ('Oleg', 3);

insert into users(name, role_id) values ('Den', 3);

insert into users(name, role_id) values ('Stas', 2);

