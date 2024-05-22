create table if not exists customers
(
    id       bigserial
        primary key,
    address  varchar(255),
    amount   integer,
    name     varchar(255),
    shipment boolean
    CONSTRAINT amount CHECK (amount > 0)
);


create table if not exists genres
(
    id   bigserial
        primary key,
    name varchar(255)
);


create table if not exists musicians
(
    id      bigserial
        primary key,
    country varchar(255),
    name    varchar(255)
);


create table if not exists musicians_genres
(
    musician_id bigint not null
        constraint fk_musicians
            references musicians,
    genres_id   bigint not null
        constraint fk_genres
            references genres
);


create table if not exists producers
(
    id      bigserial
        primary key,
    country varchar(255),
    name    varchar(255)
);


create table if not exists records
(
    id   bigserial
        primary key,
    name varchar(255)
);


create table if not exists records_copy
(
    id           bigserial
        primary key,
    price_in     integer,
    price_out    integer,
    rarity       integer,
    sold         boolean,
    supplier     varchar(255),
    year         integer,
    customer_id  bigint
        constraint fk_customer
            references customers,
    producers_id bigint
        constraint fk_producers
            references producers,
    record_id    bigint
        constraint fk_records
            references records
        CONSTRAINT year CHECK (year > 0)
        CONSTRAINT price_in CHECK (price_in > 0)
        CONSTRAINT price_out CHECK (price_out > 0)
);


create table if not exists records_genres
(
    records_id bigint not null
        constraint fk_records_genres_records
            references records,
    genres_id  bigint not null
        constraint fk_records_genres_genres
            references genres
);

create table if not exists records_musicians
(
    musicians_id bigint not null
        constraint fk_records_musicians_musicians
            references records,
    records_id   bigint not null
        constraint fk_records_musicians_records
            references musicians
);

create table if not exists songs
(
    id          bigserial
        primary key,
    name        varchar(255),
    year        integer,
    genre_id    bigint
        constraint fk_songs_genres
            references genres,
    musician_id bigint
        constraint fk_songs_musicians
            references musicians
);


create table if not exists records_songs
(
    song_id    bigint not null
        constraint fk_records_songs_songs
            references songs,
    records_id bigint not null
        constraint fk_records_songs_records
            references records
);


