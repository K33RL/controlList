CREATE TABLE users (
    id serial primary key,
    name varchar(100) not null,
    surname varchar(100) not null,
--    password varchar(256) not null,
--    password_salt varchar(16) not null,
    constraint u_constraint unique (name, surname)
);

CREATE TABLE activities (
    id serial primary key,
    user_id integer references users not null,
    start_time timestamptz not null,
    end_time timestamptz not null,
    description varchar(256) NOT NULL CHECK (description <> '')
);