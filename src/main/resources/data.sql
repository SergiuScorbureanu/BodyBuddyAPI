INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(id, username, email, password)
VALUES('69cabc81-3b80-4765-a6fd-c2ce6d93a4fe', 'admin', 'admin@admin.com', '$2a$12$Q.nxrXSE0EoaSgFJKLcLYuzr2RYclqqWmmy6TmJ4pDo5EZP50p1be');

INSERT INTO user_params(birth_day, creation_date, height, updated_date, weight, id, user_id, gender, training_type, weight_goal)
VALUES('10-07-1990', '10-07-1990', 185.00, '10-07-1995', 85.00, '69cabc81-3b80-4765-a6fd-c2ce6d93a123', '69cabc81-3b80-4765-a6fd-c2ce6d93a4fe', 'MALE', 'SEDENTARY', 'GAIN_WEIGHT');

INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id from users u, roles r
WHERE u.username = 'admin' AND r.name IN ('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN');

--Foods
INSERT INTO food(id, name, calories, carbohydrates, protein, fat, measurement_unit, quantity)
VALUES('7277e5b2-fd56-4db7-af2b-5afd07f5749c', 'Chicken breast', 165, 0, 31, 3.6, 'grams', 100);

INSERT INTO food(id, name, calories, carbohydrates, protein, fat, measurement_unit, quantity)
VALUES('7277e5b2-fd56-4db7-af2b-5afd07f5749d', 'Chicken legs', 209, 0, 26, 10.9, 'grams', 100);

INSERT INTO food(id, name, calories, carbohydrates, protein, fat, measurement_unit, quantity)
VALUES('7277e5b2-fd56-4db7-af2b-5afd07f5749e','Chicken Eggs', 72, 0.4, 6.3, 4.8, 'piece', 1);

INSERT INTO food(id, name, calories, carbohydrates, protein, fat, measurement_unit, quantity)
VALUES('7277e5b2-fd56-4db7-af2b-5afd07f5749f','Milk', 65, 4.7, 3.2, 3.5, 'ml', 100);

INSERT INTO food(id, name, calories, carbohydrates, protein, fat, measurement_unit, quantity)
VALUES('7277e5b2-fd56-4db7-af2b-5afd07f5724c','Boiled Rice', 130, 28.2, 2.7, 0.3, 'grams', 100);
