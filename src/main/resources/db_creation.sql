CREATE TABLE users (
	id SERIAL NOT NULL PRIMARY KEY,
    name  varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE
);

CREATE TABLE events (
	id SERIAL NOT NULL PRIMARY KEY,
    title varchar(255) NOT NULL,
    date DATE NOT NULL,
    ticket_price DECIMAL NOT NULL
);

CREATE TABLE tickets (
	id SERIAL NOT NULL PRIMARY KEY,
    event_id int references events (id),
    user_id  int references users (id),
    category varchar(255) NOT NULL,
    place int NOT NULL
);

CREATE TABLE user_accounts (
	id SERIAL NOT NULL PRIMARY KEY,
    user_id int references users (id) UNIQUE,
    money DECIMAL
);