INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(id, username, email, password)
VALUES('69cabc81-3b80-4765-a6fd-c2ce6d93a4fe', 'admin', 'admin@admin.com', '$2a$12$Q.nxrXSE0EoaSgFJKLcLYuzr2RYclqqWmmy6TmJ4pDo5EZP50p1be');

INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id from users u, roles r
WHERE u.username = 'admin' AND r.name IN ('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN');

--INSERT INTO "trainings"(id, title, duration, content)
--VALUES ('65766440-019c-4817-8b43-084b97eb3b77', 'Chest', 30, 'Chest training plm');
--
--
--INSERT INTO "exercises"(id, training_id, name, set_number, rep_number, pause_time)
--VALUES ('de1ebde8-f4ea-41f9-a96a-44684c078b66', '65766440-019c-4817-8b43-084b97eb3b77', 'Bench press', 4, 12, 2);
--
--INSERT INTO "exercises"(id, training_id, name, set_number, rep_number, pause_time)
--VALUES ('b5b5b8a5-3088-445c-820b-9c5a8a88256b', '65766440-019c-4817-8b43-084b97eb3b77', 'Cable fly', 3, 12, 2);
--
--INSERT INTO "exercises"(id, name, set_number, rep_number, pause_time)
--VALUES ('acad7a09-7d89-47e6-9f3e-5c0272217655', 'Push-Up', 3, 20, 2);