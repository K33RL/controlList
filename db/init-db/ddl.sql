CREATE TABLE users (
    id serial primary key,
    name varchar(100) not null,
    surname varchar(100) not null,
    constraint u_constraint unique (name, surname)
);

CREATE TABLE activities (
    id serial primary key,
    user_id integer references users not null,
    duration_min integer CHECK (duration_min > 0),
    description varchar(256) NOT NULL CHECK (description <> '')
);