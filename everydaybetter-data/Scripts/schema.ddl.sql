DROP TABLE IF EXISTS t_tracking_logs;
DROP TABLE IF EXISTS t_activities_categories;
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
	email VARCHAR(200) NOT NULL UNIQUE,
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
    CONSTRAINT t_activities_pkey PRIMARY KEY(id),   
    CONSTRAINT t_activities_activity_name_user_id_ukey UNIQUE (activity_name, user_id),
    CONSTRAINT t_activities_users_fkey FOREIGN KEY (user_id) REFERENCES t_users(id)
);

CREATE TABLE t_tracking_logs(
	id INT GENERATED ALWAYS AS IDENTITY,
	tracked_day timestamp NOT NULL,
	is_pratice boolean NOT NULL,
	activity_id INT NOT NULL,
	CONSTRAINT t_tracking_logs_pkey PRIMARY KEY(id),
	CONSTRAINT t_tracking_logs_tracked_day_activity_id_ukey UNIQUE (tracked_day, activity_id),
	CONSTRAINT t_tracking_logs_activities_fkey FOREIGN KEY (activity_id) REFERENCES t_activities (id)
);

CREATE TABLE t_activities_categories(
	activity_id INT NOT NULL,
	category_id INT NOT NULL,
	CONSTRAINT t_activities_categories_activities_fkey FOREIGN KEY (activity_id) REFERENCES t_activities (id),
	CONSTRAINT t_activities_categories_categories_fkey FOREIGN KEY (category_id) REFERENCES t_categories (id),
	CONSTRAINT t_activities_categories_ukey UNIQUE (activity_id, category_id)
);

CREATE TABLE t_roles(
	id INT GENERATED ALWAYS AS IDENTITY,
	role_name varchar(200)NOT NULL UNIQUE,
	is_default boolean DEFAULT FALSE,
	CONSTRAINT t_role_pkey PRIMARY KEY (id),
	CONSTRAINT t_role_ukey UNIQUE (role_name)
	);

CREATE TABLE t_users_roles(
	id INT GENERATED ALWAYS AS IDENTITY,
	user_id INT NOT NULL,
	role_id INT NOT NULL,
	CONSTRAINT t_users_roles_pkey PRIMARY KEY(id),
	CONSTRAINT t_users_roles_ukey UNIQUE (user_id, role_id),
	CONSTRAINT t_users_roles_users_fkey FOREIGN KEY (user_id) REFERENCES t_users(id),
	CONSTRAINT t_users_roles_roles_fkey FOREIGN KEY (role_id) REFERENCES t_roles(id)
);