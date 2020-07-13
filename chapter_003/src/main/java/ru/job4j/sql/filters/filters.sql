--скрипты запросов по заданию

--Написать запрос получение всех продуктов с типом "СЫР"
select * from product as p inner join type as t on p.type_id=t.id where t.name='СЫР';

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where product.name like '%Мороженое%';

--Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where product.expired_date between '2020-08-01 00:00:00' and '2020-08-31 23:59:59';

--Написать запрос, который выводит самый дорогой продукт.
select * from product where product.price = (select max(price) from product);

--Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*) from product inner join type on product.type_id=type.id where type.name='МОЛОКО';

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product inner join type on product.type_id=type.id where type.name='МОЛОКО' or type.name='СЫР';

--Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select * from product where product.amount < 10;

--Вывести все продукты и их тип.
select * from product inner join type on product.type_id=type.id;