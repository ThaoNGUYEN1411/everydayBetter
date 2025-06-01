DELETE FROM t_tracking_record;
DELETE FROM t_activities_categories;
DELETE FROM t_activities;
DELETE FROM t_users_roles;
DELETE FROM t_users;
DELETE FROM t_categories;
DELETE FROM t_roles;


INSERT INTO t_roles (role_name, is_default) VALUES
	('ROLE_USER', true), ('ROLE_ADMIN', false);

INSERT INTO t_categories (category_name) VALUES 
('Santé et Bien-être'), ('Productivité et Travail'), ('Vie quotidienne');

INSERT INTO t_categories (category_name) VALUES ('test1'), ('test2');

SELECT * FROM t_activities;
SELECT * FROM t_categories tr ;
SELECT * FROM t_activities_categories th ;
SELECT * FROM t_users tu  ;
SELECT * FROM t_roles ;
SELECT * FROM t_tracking_logs;

SELECT * FROM t_tracking_record t WHERE activity_id =29;

SELECT tracked_date , done  FROM t_tracking_record t WHERE t.activity_id =22;

DELETE FROM t_tracking_record WHERE tracked_date = '2025-06-01' AND activity_id = '32';



