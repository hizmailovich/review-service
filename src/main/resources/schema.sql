CREATE TABLE IF NOT EXISTS reviews
(
    id       bigserial    NOT NULL,
    name     varchar(50)  NOT NULL,
    text     varchar(150) NOT NULL,
    date     date         NOT NULL,
    movie_id bigint       NOT NULL,
    PRIMARY KEY (id)
);