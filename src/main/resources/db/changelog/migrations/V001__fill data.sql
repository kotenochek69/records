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



CREATE OR REPLACE PROCEDURE add_customer(
    p_name VARCHAR(255),
    p_address VARCHAR(255),
    p_shipment BOOLEAN,
    p_amount INTEGER
)
LANGUAGE SQL
AS $$
INSERT INTO customers (name, address, shipment, amount)
VALUES (p_name, p_address, p_shipment, p_amount);
$$;


