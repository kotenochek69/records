


-- SELECT rarity, sold FROM records_copy;
--
-- SELECT * FROM records_copy
-- WHERE (rarity > 1);
--
-- SELECT rarity, name FROM records_copy, musicians;
--
-- SELECT * FROM musicians
-- JOIN producers ON musicians.country=producers.country;
--
-- SELECT * FROM musicians
-- LEFT JOIN producers ON musicians.country=producers.country;
--
-- SELECT price_in FROM records_copy
-- GROUP BY price_in;
--
-- SELECT rarity, AVG(price_in) as price FROM records_copy
-- GROUP BY rarity
-- HAVING AVG(price_in) > 2000;

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
