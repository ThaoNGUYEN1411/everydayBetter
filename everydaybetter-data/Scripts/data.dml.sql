DELETE FROM t_tracking_logs;
DELETE FROM t_activities_categories;
DELETE FROM t_activities;
DELETE FROM t_users_roles;
DELETE FROM t_users;
DELETE FROM t_categories;
DELETE FROM t_roles;


INSERT INTO t_roles (role_name, is_default) VALUES
	('ROLE_USER', true), ('ROLE_ADMIN', false);

INSERT INTO t_categories (category_name) VALUES ('Ecole'), ('Santé');

SELECT * FROM t_activities;
SELECT * FROM t_categories tr ;
SELECT * FROM t_activities_categories th ;
SELECT * FROM t_users tu  ;
SELECT * FROM t_roles ;
SELECT * FROM t_users_roles;
