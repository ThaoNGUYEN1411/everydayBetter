DELETE FROM t_tracking_logs ;
DELETE FROM t_activities;
DELETE FROM t_users_roles;
DELETE FROM t_users;
DELETE FROM t_articles ;
DELETE FROM t_authors ;
DELETE FROM t_categories;
DELETE FROM t_roles;

INSERT INTO t_roles (role_name, is_default) VALUES
	('ROLE_USER', true), ('ROLE_ADMIN', false);

INSERT INTO t_categories (category_name) VALUES 
('Santé et Bien-être'), ('Productivité et Travail'), ('Vie quotidienne'), ('Habitudes financières');
INSERT INTO t_categories (category_name) VALUES ('Autres');
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

INSERT INTO t_authors (author_name) VALUES
('Jean Dupont'),
('Marie Curie'),
('Paul Martin');
SELECT *FROM t_articles ta ;
INSERT INTO t_articles 
(title, sub_title, introduction, content, image, thumbnail_image, published_date, author_id, category_id) 
VALUES
(
    '5 Exercices pour rester en forme à la maison',
    'Restez actif sans équipement',
    'Découvrez comment garder la forme même sans salle de sport grâce à des exercices simples à pratiquer chez vous.',
	'Pour rester en forme, commencez par un échauffement léger comme marcher sur place, faire des rotations des bras et quelques étirements. Enchaînez ensuite avec des exercices de renforcement musculaire : squats, pompes, abdos. Réalisez 3 séries de 10 répétitions avec 30 secondes de repos entre chaque série. Terminez par des étirements et des exercices de respiration afin de détendre vos muscles et améliorer votre bien-être général. Pour optimiser vos résultats, veillez également à adopter une bonne hygiène de vie : buvez suffisamment d’eau, dormez entre 7 et 8 heures par nuit et privilégiez une alimentation équilibrée riche en fruits, légumes et protéines. La régularité est la clé : même de courtes séances pratiquées plusieurs fois par semaine auront un impact positif sur votre énergie et votre santé.',
    'exercices_maison.webp',
    'exercices_maison_thumb.webp',
    '2025-09-01 00:00:00', --Date AND time
    1,
    1
),
(
    'Yoga quotidien pour débutants',
    'Commencez votre pratique doucement',
    'Découvrez les bienfaits du yoga pour la souplesse, la relaxation et la concentration.',
	'Pour débuter, consacrez 15 à 20 minutes par jour à des postures simples comme la posture de l’enfant, le chien tête en bas et la posture du cobra. Concentrez-vous sur votre respiration et gardez un rythme régulier pour maximiser les bénéfices. Avec une pratique régulière, vous améliorerez votre souplesse, votre équilibre et réduirez le stress quotidien.
Pour aller plus loin, vous pouvez progressivement intégrer des postures plus avancées ou prolonger la durée de vos séances. N’oubliez pas d’écouter votre corps et de respecter vos limites afin d’éviter les blessures. La persévérance et la patience sont essentielles pour progresser en douceur et profiter pleinement des bienfaits du yoga.',
    'yoga_debutant.webp',
    'yoga_debutant_thumb.webp',
    '2025-08-06 10:00:00',
    1,
    1
),
(
    'Organiser sa journée pour être plus productif',
    'Méthodes simples et efficaces',
    'Améliorez votre productivité grâce à des techniques faciles à mettre en place au quotidien.',
	'Commencez votre journée en définissant vos priorités et en listant les tâches importantes. Utilisez la technique Pomodoro : travaillez 25 minutes, puis faites 5 minutes de pause pour rester concentré. Optimisez votre espace de travail, limitez les distractions et suivez vos progrès pour améliorer continuellement votre efficacité.
Pensez également à célébrer vos petites réussites au quotidien. Cela renforce la motivation et vous aide à garder un état d’esprit positif face aux défis. En adoptant ces habitudes, vous développerez une meilleure organisation et gagnerez en sérénité dans votre travail.',
	'productivite.webp',
    'productivite_thumb.webp',
    '2025-08-05 00:00:00',
    1,
    2
),
(
    'Gérer efficacement ses emails',
    'Moins de stress, plus de temps',
    'Apprenez à organiser votre boîte mail pour gagner du temps et réduire le stress.',
	'Créez des dossiers par priorité et par sujet pour trier vos messages. Définissez des plages horaires pour consulter vos emails et évitez les notifications constantes. Supprimez régulièrement les messages inutiles et répondez rapidement aux urgents pour rester maître de votre boîte mail.
	Pour aller plus loin, pensez à utiliser des outils ou filtres automatiques pour classer vos emails et réduire le temps passé à trier votre messagerie. Adopter ces bonnes pratiques vous permettra de gagner en efficacité, de réduire le stress lié à l’accumulation des messages et de mieux vous concentrer sur vos tâches essentielles.',
    'emails.webp',
    'emails_thumb.webp',
    '2025-06-01 09:00:00',
    1,
    2
),
(
    '10 astuces pour mieux dormir',
    'Améliorez votre qualité de sommeil',
    'Un bon sommeil est essentiel pour la santé physique et mentale. Voici quelques conseils pour mieux dormir.',
	'Établissez une routine du soir en vous couchant et en vous levant à la même heure chaque jour. Évitez les écrans et la caféine avant le coucher, et privilégiez un moment calme et relaxant. Aménagez une chambre confortable, fraîche et sombre pour favoriser un sommeil réparateur.',
    'dormir_bien.webp',
    'dormir_bien_thumb.webp',
    '2025-06-02 09:00:00',
    2,
    3
),
(
    'Planifier ses repas de la semaine',
    'Gagnez du temps et mangez sainement',
    'Organiser vos repas à l’avance permet de mieux gérer votre alimentation et votre budget.',
	'Faites une liste des repas pour chaque jour de la semaine et notez les ingrédients nécessaires. Préparez certains plats à l’avance pour réduire le temps de cuisson quotidien. Variez les aliments pour équilibrer les repas et inclure tous les nutriments essentiels.',
    'repas_semaine.webp',
    'repas_semaine_thumb.webp',
    '2025-06-03 09:00:00',
    1,
    3
),
(
    'Économiser facilement chaque mois',
    'Conseils pratiques pour vos finances',
    'Adoptez des habitudes simples pour mettre de l’argent de côté chaque mois.',
	'Commencez par suivre vos dépenses pour identifier où vous pouvez économiser. Fixez un montant fixe à mettre de côté dès la réception de votre salaire. Évitez les achats impulsifs et privilégiez les solutions économiques pour les achats courants. Pour renforcer vos économies, établissez un budget mensuel détaillé et revoyez-le régulièrement afin d’ajuster vos dépenses. Adopter ces bonnes pratiques vous aidera à mieux gérer votre argent, à constituer une épargne solide et à atteindre plus facilement vos objectifs financiers.',
    'economie.webp',
    'economie_thumb.webp',
    '2025-06-03 09:10:00',
    1,
    4
),
(
    'Créer un budget familial',
    'Planifiez vos finances sereinement',
    'Un budget clair permet de mieux gérer ses revenus et dépenses.',
    'Listez toutes vos sources de revenus et vos dépenses fixes mensuelles. Allouez un montant pour les loisirs, l’épargne et les imprévus.Révisez votre budget chaque mois pour ajuster vos dépenses et atteindre vos objectifs financiers.',
    'budget_familial.webp',
    'budget_familial_thumb.webp',
    '2025-06-03 09:00:05',
    1,
    4
),
(
    '5 conseils pour mieux gérer son stress',
    'Retrouvez calme et sérénité',
    'Le stress peut affecter la santé et la productivité, voici des stratégies pour le réduire.',
    'Pratiquez la respiration profonde et la méditation pour apaiser l’esprit. Faites régulièrement de l’exercice physique pour libérer les tensions.Planifiez vos tâches et prenez du temps pour vous détendre afin de mieux gérer la pression quotidienne.',
    'gestion_stress.webp',
    'gestion_stress_thumb.webp',
    '2025-08-03 09:00:00',
    2,
    5
),
(
    'Comment rester motivé sur le long terme',
    'Techniques pour atteindre vos objectifs',
    'La motivation est clé pour réussir vos projets personnels et professionnels.',
    'Fixez des objectifs clairs et réalistes à court et long terme. Célébrez vos petites réussites pour maintenir votre enthousiasme. Entourez-vous de personnes positives et inspirez-vous de modèles pour rester motivé chaque jour.',
    'motivation.webp',
    'motivation_thumb.webp',
    '2025-07-03 09:00:00',
    3,
    5
);


