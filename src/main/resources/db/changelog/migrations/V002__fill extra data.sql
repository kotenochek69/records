INSERT INTO records
(name)
VALUES
    ('Imagine'),
    ('Curtain Call'),
    ('The very Best of the Platters'),
    ('The Ultimate collection'),
    ('All things must pass');

INSERT INTO genres
(name)
VALUES
    ('pop'),
    ('rock'),
    ('rap'),
    ('hip-hop');

INSERT INTO songs
(name, year)
VALUES
    ('Imagine', 1971),
    ('Crippled Inside', 1971),
    ('Jelous Guy', 1971),
    ('Intro', 2005),
    ('Only you', 2020),
    ('Paranoid', 2016),
    ('My sweet lord', 1970);

INSERT INTO producers
(name, country)
VALUES
    ('Apple', 'England'),
    ('Shady', 'England'),
    ('Not Now Music', 'Europe'),
    ('BMG', 'Europe'),
    ('Capitol', 'Europe');

INSERT INTO customers
(name, address, shipment, amount)
VALUES
    ('Валентин Стрыко', 'Пирогова 2', true, 1000),
    ('Маша Малфой', 'Пирогова 23', true, 10005),
    ('Алла Хабкар', 'Ильича 6', true, 2000),
    ('Денис Пип', 'Ильича 2', true, 6000),
    ('Алексей Жидков', 'Пирогова 1', true, 1000000);



INSERT INTO records_copy
(price_in, price_out, rarity, sold, supplier, year)
VALUES
    (1000, 1501, 3, true, 'RecordsShop', 2015),
    (2000, 2500, 2, true, 'RecordsShop', 2021),
    (1000, 1500, 1, true, 'RecordsShop', 2020),
    (3000, 3500, 2, true, 'RecordsShop', 2023),
    (4000, 4500, 3, true, 'RecordsShop', 2021);

INSERT INTO musicians
(name, country)
VALUES
    ('John Lennon', 'England'),
    ('Eminem', 'USA'),
    ('Platters', 'USA'),
    ('Black Sabbath', 'England'),
    ('George Harrison', 'England');

SELECT rarity, sold FROM records_copy;

SELECT * FROM records_copy
WHERE (rarity > 1);

SELECT rarity, name FROM records_copy, musicians;

SELECT * FROM musicians
JOIN producers ON musicians.country=producers.country;

SELECT * FROM musicians
LEFT JOIN producers ON musicians.country=producers.country;

SELECT price_in FROM records_copy
GROUP BY price_in;

SELECT rarity, AVG(price_in) as price FROM records_copy
GROUP BY rarity
HAVING AVG(price_in) > 2000;

SELECT * FROM musicians
ORDER BY name;

SELECT COUNT(*) FROM musicians;


-- SELECT musician_id, musician_name, ARRAY_AGG(record_name) AS records
-- FROM (
--          SELECT m.id AS musician_id, m.name AS musician_name,
--                 r.name AS record_name,
--                 ROW_NUMBER() OVER (PARTITION BY m.id ORDER BY r.id) AS row_num
--          FROM musicians m
--                   JOIN records_musicians rm ON m.id = rm.musicians_id
--                   JOIN records r ON rm.records_id = r.id
--      ) ranked_records
-- WHERE row_num <= 10
-- GROUP BY musician_id, musician_name
-- ORDER BY musician_id, row_num;
