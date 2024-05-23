CREATE OR REPLACE FUNCTION update_amount_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' AND NEW.shipment = true THEN
UPDATE customers
SET amount = amount + 200
WHERE id = NEW.id;
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER customers_insert_trigger
    AFTER INSERT ON customers
    FOR EACH ROW
    EXECUTE FUNCTION update_amount_on_insert();

INSERT INTO musicians
(name, country)
VALUES
    ('John Lennon', 'England'),
    ('Eminem', 'USA'),
    ('Platters', 'USA'),
    ('Black Sabbath', 'England'),
    ('George Harrison', 'England'),
    ('Harris Simon', 'England'),
    ('Metallica', 'USA');

INSERT INTO records
(name)
VALUES
    ('Imagine'),
    ('Curtain Call'),
    ('The very Best of the Platters'),
    ('The Ultimate collection'),
    ('All things must pass'),
    ('Short conversation'),
    ('Master of Puppets');

INSERT INTO records_musicians
(musicians_id, records_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7);

INSERT INTO records_musicians
(musicians_id, records_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7);


INSERT INTO genres
(name)
VALUES
    ('pop'),
    ('rock'),
    ('rap'),
    ('hip-hop');

INSERT INTO records_genres
(records_id, genres_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 2),
    (6, 1),
    (7, 3);

INSERT INTO songs
(name, year, genre_id, musician_id)
VALUES
    ('Imagine', 1971, 1, 1),
    ('Crippled Inside', 1971, 2, 2),
    ('Jealous Guy', 1971, 2, 3),
    ('Intro', 2005, 3, 4),
    ('Only you', 2020, 1, 5),
    ('Paranoid', 2016, 4, 6),
    ('My sweet lord', 1970, 4, 6),
    ('Dream no more', 1990, 3, 7);

INSERT INTO records_songs
(song_id, records_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 4);

INSERT INTO producers
(name, country)
VALUES
    ('Apple', 'England'),
    ('Shady', 'England'),
    ('Not Now Music', 'Europe'),
    ('BMG', 'Europe'),
    ('Capitol', 'Europe');

CREATE PROCEDURE add_customer(
    p_name VARCHAR(255),
    p_address VARCHAR(255),
    p_shipment BOOLEAN,
    p_amount INTEGER
)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO customers (name, address, shipment, amount)
VALUES (p_name, p_address, p_shipment, p_amount);
END;
$$;

    CALL add_customer('Валентин Стрыко', 'Пирогова 2', true, 1000);
    CALL add_customer('Маша Малфой', 'Пирогова 23', true, 10005);
    CALL add_customer('Алла Женькова', '', false, 2000);
    CALL add_customer('Денис Пип', 'Ильича 2', true, 6000);
    CALL add_customer('Алексей Жидков', 'Пирогова 1', true, 1000000);









