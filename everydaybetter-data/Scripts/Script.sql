DROP TABLE IF EXISTS t_labels;
DROP TABLE IF EXISTS t_accounts;
DROP TABLE IF EXISTS t_habit_tracking;
DROP TABLE IF EXISTS t_habits;

CREATE TABLE t_labels(
	id_label INT GENERATED ALWAYS AS IDENTITY,
	label_name VARCHAR(200) NOT NULL,
	CONSTRAINT t_label_pkey PRIMARY KEY(id_label),
	CONSTRAINT t_label_unique UNIQUE (label_name)
);

CREATE TABLE t_accounts(
	id_account INT GENERATED ALWAYS AS IDENTITY,
	user_name VARCHAR(200),
	email VARCHAR(200) NOT NULL UNIQUE,
	password VARCHAR(255) --length PASSWORD hash
);


CREATE TABLE t_habit_tracking(
	id_habit_tracking INT GENERATED ALWAYS AS IDENTITY,
	date_track timestamp NOT NULL,
	is_pratice boolean NOT NULL,
	CONSTRAINT t_habits_pkey PRIMARY KEY(id_habit_tracking),
	CONSTRAINT t_habits_ukey UNIQUE (date_track)
);

CREATE TABLE t_habits(
	id_habit INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    habit_name VARCHAR(200) NOT NULL,
    description VARCHAR(2000),
    positive BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),
    label_id INT,
    account_id INT,
    habit_tracking_id INT,
    CONSTRAINT t_habit_ukey UNIQUE (habit_name),
    CONSTRAINT t_habits_label_id_fkey FOREIGN KEY (label_id) REFERENCES t_labels(id_label),
    CONSTRAINT t_habits_account_id_fkey FOREIGN KEY (account_id) REFERENCES t_accounts(id_account),
    CONSTRAINT t_habits_habit_tracking_id_fkey FOREIGN KEY (habit_tracking_id) REFERENCES t_habit_tracking(id_habit_tracking)
);