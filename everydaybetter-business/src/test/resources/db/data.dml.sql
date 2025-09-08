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

INSERT INTO t_authors (author_name) VALUES
('Jean Dupont'),
('Marie Curie'),
('Paul Martin');

INSERT INTO t_articles
(title, sub_title, introduction, content, image, thumbnail_image, published_date, author_id, category_id)
VALUES
(
    '5 Exercices pour rester en forme à la maison',
    'Restez actif sans équipement',
    'Découvrez comment garder la forme même sans salle de sport grâce à des exercices simples à pratiquer chez vous.',
    'Pour rester en forme, commencez par un échauffement léger comme marcher sur place, faire des rotations des bras et quelques étirements.\n\nEnchaînez ensuite des exercices de renforcement musculaire : squats, pompes, abdos. Faites 3 séries de 10 répétitions avec 30 secondes de repos entre chaque série.\n\nTerminez par des étirements et des exercices de respiration pour détendre vos muscles et améliorer votre bien-être général.',
    'exercices_maison.jpg',
    'exercices_maison_thumb.jpg',
    '2025-09-01', --Date AND time
    1,
    2
),
(
    'Yoga quotidien pour débutants',
    'Commencez votre pratique doucement',
    'Découvrez les bienfaits du yoga pour la souplesse, la relaxation et la concentration.',
    'Pour débuter, consacrez 15 à 20 minutes par jour à des postures simples comme la posture de l’enfant, le chien tête en bas et la posture du cobra.\n\nConcentrez-vous sur votre respiration et gardez un rythme régulier pour maximiser les bénéfices.\n\nAvec une pratique régulière, vous améliorerez votre souplesse, votre équilibre et réduirez le stress quotidien.',
    'yoga_debutant.jpg',
    'yoga_debutant_thumb.jpg',
    '2025-09-01',
    2,
    3
);
