--specific schema for test
CREATE SCHEMA schemas_test;

--specific DDL for test
DROP TABLE IF EXISTS t_tracking_logs;
DROP TABLE IF EXISTS t_activities;
DROP TABLE IF EXISTS t_users_roles;
DROP TABLE IF EXISTS t_users;
DROP TABLE IF EXISTS t_categories;
DROP TABLE IF EXISTS t_roles;

CREATE TABLE t_categories(
	id INT GENERATED ALWAYS AS IDENTITY,
	category_name VARCHAR(200) NOT NULL,
	CONSTRAINT t_categories_pkey PRIMARY KEY(id),
	CONSTRAINT t_categories_unique UNIQUE (category_name)
);

CREATE TABLE t_users(
	id INT GENERATED ALWAYS AS IDENTITY,
	email VARCHAR(200) NOT NULL,
	nickname VARCHAR(200),
	password VARCHAR(255),
	CONSTRAINT t_users_pkey PRIMARY KEY(id),
	CONSTRAINT t_users_email_ukey UNIQUE (email)
);

CREATE TABLE t_activities(
	id INT GENERATED ALWAYS AS IDENTITY,
    activity_name VARCHAR(200) NOT NULL,
    description VARCHAR(2000),
    is_positive BOOLEAN NOT NULL,
    user_id INT,
    category_id INT,
    CONSTRAINT t_activities_pkey PRIMARY KEY(id),   
    CONSTRAINT t_activities_activity_name_user_id_ukey UNIQUE (activity_name, user_id),
    CONSTRAINT t_activities_users_fkey FOREIGN KEY (user_id) REFERENCES t_users(id),
    CONSTRAINT t_activities_categories_fkey FOREIGN KEY (category_id) REFERENCES t_categories (id)
);

CREATE TABLE t_tracking_logs(
	id INT GENERATED ALWAYS AS IDENTITY,
	tracked_date date NOT NULL,
	done boolean NOT NULL,
	activity_id INT NOT NULL,
	CONSTRAINT t_tracking_logs_pkey PRIMARY KEY(id),
	CONSTRAINT t_tracking_logs_tracked_date_activity_id_ukey UNIQUE (tracked_date, activity_id),
	CONSTRAINT t_tracking_logs_activities_fkey FOREIGN KEY (activity_id) REFERENCES t_activities (id)
);

CREATE TABLE t_roles(
	id INT GENERATED ALWAYS AS IDENTITY,
	role_name varchar(200)NOT NULL UNIQUE,
	is_default boolean DEFAULT FALSE,
	CONSTRAINT t_role_pkey PRIMARY KEY (id),
	CONSTRAINT t_role_ukey UNIQUE (role_name)
	);

CREATE TABLE t_users_roles(
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	CONSTRAINT t_users_roles_ukey PRIMARY KEY (user_id, role_id),
	CONSTRAINT t_users_roles_users_fkey FOREIGN KEY (user_id) REFERENCES t_users(id),
	CONSTRAINT t_users_roles_roles_fkey FOREIGN KEY (role_id) REFERENCES t_roles(id)
);

--specific DML for test
DELETE FROM t_tracking_logs ;
DELETE FROM t_activities;
DELETE FROM t_users_roles;
DELETE FROM t_users;
DELETE FROM t_categories;
DELETE FROM t_roles;

INSERT INTO t_roles (role_name, is_default) VALUES
	('ROLE_USER', true), ('ROLE_ADMIN', false);

INSERT INTO t_categories (category_name) VALUES 
('Santé et Bien-être'), ('Productivité et Travail'), ('Vie quotidienne'), ('Habitudes financières');

INSERT INTO t_users (email, nickname, "password") VALUES
('lucia@gmail.com', 'Lucia', '$2a$12$srmwxQaHT9x3HlBRZS32KeiRoPw/H./0TE0U7R.jt0.YEpqvAign2'),
('tom@gmail.com', 'Tom', '$2a$12$8WLHJKVTghWR5xYJwKLHauHVUuhx0o5lbpxJPWmdu.EnysgBCQ/MW');

INSERT INTO t_users_roles (user_id, role_id) VALUES (
(SELECT tu.id FROM t_users tu WHERE tu.email = 'lucia@gmail.com'),
(SELECT tr.id FROM t_roles tr WHERE tr.role_name = 'ROLE_USER')),
((SELECT tu.id FROM t_users tu WHERE tu.email = 'tom@gmail.com'),
(SELECT tr.id FROM t_roles tr WHERE tr.role_name = 'ROLE_ADMIN'));

INSERT INTO t_activities (activity_name, description, is_positive, user_id, category_id) VALUES
('Lire des livres', 'Lire quelques pages d’un livre', TRUE, 
(SELECT tu.id FROM t_users tu WHERE tu.email = 'lucia@gmail.com' ),
(SELECT tc.id FROM t_categories tc WHERE tc.category_name = 'Vie quotidienne'));

INSERT INTO t_tracking_logs (tracked_date, done, activity_id) VALUES
('2025-06-09', TRUE, (SELECT ta.id FROM t_activities ta INNER JOIN t_users tu ON ta.user_id = tu.id 
WHERE ta.activity_name = 'Lire des livres' AND tu.email = 'lucia@gmail.com' ));