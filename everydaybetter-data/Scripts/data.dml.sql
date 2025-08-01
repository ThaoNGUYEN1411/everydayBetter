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
('amy@gmail.com', 'Amy', '$2a$12$Eot6uSrWvDJRj5srcCNUoe.nlqY/UaFrzpUKDv9A6jo2RWpnsD30e'),
('antony@gmail.com', 'Antony', '$2a$12$nueZkRkDYdXQSqJ2Lm1.2u5Q/M5WA0WqvEd6ofO9JZfC5mtjU7s8u');

INSERT INTO t_users_roles (user_id, role_id) VALUES (
(SELECT tu.id FROM t_users tu WHERE tu.email = 'amy@gmail.com'),
(SELECT tr.id FROM t_roles tr WHERE tr.role_name = 'ROLE_USER')),
((SELECT tu.id FROM t_users tu WHERE tu.email = 'antony@gmail.com'),
(SELECT tr.id FROM t_roles tr WHERE tr.role_name = 'ROLE_ADMIN'));

INSERT INTO t_activities (activity_name, description, is_positive, user_id, category_id) VALUES
('Lire des livres', 'Lire quelques pages d’un livre', TRUE, 
(SELECT tu.id FROM t_users tu WHERE tu.email = 'amy@gmail.com' ),
(SELECT tc.id FROM t_categories tc WHERE tc.category_name = 'Vie quotidienne'));

INSERT INTO t_tracking_logs (tracked_date, done, activity_id) VALUES
('2025-06-09', TRUE, (SELECT ta.id FROM t_activities ta INNER JOIN t_users tu ON ta.user_id = tu.id 
WHERE ta.activity_name = 'Lire des livres' AND tu.email = 'amy@gmail.com' ));
