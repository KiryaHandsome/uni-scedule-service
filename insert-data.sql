INSERT INTO role (role)
VALUES ('Admin'),
       ('User');

INSERT INTO "user" (role_id, email, password)
VALUES (1, 'admin@example.com', 'admin_password'),
       (2, 'user@example.com', 'user_password');

INSERT INTO subject (name, short_name)
VALUES ('Архитектура вычислительных систем', 'АВС'),
       ('Верификация программного обеспечения', 'ВПО'),
       ('Современные платформы прикладной разработки', 'СППР'),
       ('Модели данных и системы управления базами данных', 'МДиСУБД'),
       ('Операционные среды и системное программирование', 'ОСиСП'),
       ('Современные технологии разработки web-приложений', 'СТРweb-пр'),
       ('Маркетинг программного продукта и услуг', 'МППиУ');

INSERT INTO faculty (name, short_name)
VALUES ('Факультет компьютерных систем и сетей', 'ФКСИС'),
       ('Факультет информационной безопасности', 'ФИБ');

INSERT INTO classroom (number, building)
VALUES (104, 4),
       (112, 4),
       (111, 4),
       (106, 4),
       (108, 4),
       (902, 5),
       (308, 5),
       (420, 4),
       (319, 4),
       (418, 4);

INSERT INTO class_type (type, short_type)
VALUES ('Лекция', 'ЛК'),
       ('Практическое занятие', 'ПЗ'),
       ('Лабораторная работа', 'ЛР');

INSERT INTO teacher (user_id, first_name, last_name, patronymic)
VALUES (NULL, 'Вячеслав', 'Проволоцкий', 'Евгеньевич'),
       (NULL, 'Игорь', 'Гламаздин', 'Иванович'),
       (NULL, 'Светлана', 'Снисаренко', 'Валерьевна'),
       (NULL, 'Никита', 'Гриценко', 'Юрьевич'),
       (NULL, 'Анна', 'Жвакина', 'Васильевна'),
       (NULL, 'Анастасия', 'Калиновская', 'Александровна'),
       (NULL, 'Владислав', 'Плиска', 'Сергеевич'),
       (NULL, 'Александр', 'Горюшкин', 'Алексеевич'),
       (NULL, 'Алексей', 'Марков', 'Николаевич'),
       (NULL, 'Игорь', 'Чайкин', 'Сергеевич'),
       (NULL, 'Сергей', 'Сиротко', 'Иванович');

INSERT INTO teacher_subject (teacher_id, subject_id)
VALUES (1, 4),  -- Проволоцкий - БД
       (2, 3),  -- Гламаздин - СППР
       (3, 2),  -- Снисаренко - ВПО
       (4, 5),  -- Гриценко - ОСИСП
       (5, 6),  -- Жвакина - СТРВеб
       (6, 1),  -- Калиновская - АВС
       (7, 4),  -- Плиска - БД
       (8, 7),  -- Горюшкин - МПиУ
       (9, 1),  -- Марков - АВС
       (10, 2), -- Чайкин - ВПО
       (11, 5); -- Сиротко - ОСИСП

INSERT INTO class (classroom_id, teacher_id, subject_id, type_id, start_time, end_time, comment, week_numbers,
                   day_of_week)
VALUES
    -- Проволоцкий
    (5, 1, 4, 1, '19:00:00', '20:20:00', NULL, ARRAY [2], 1),
    (1, 1, 4, 1, '09:00:00', '10:20:00', NULL, ARRAY [1, 2, 3, 4], 6),
-- Гламаздин
    (5, 2, 3, 1, '15:50:00', '17:10:00', NULL, ARRAY [2, 4], 2),
    (2, 2, 3, 3, '12:25:00', '13:45:00', NULL, ARRAY [2, 4], 3),
    (2, 2, 3, 3, '14:00:00', '15:20:00', NULL, ARRAY [2, 4], 3),
    (3, 2, 3, 3, '09:00:00', '10:20:00', NULL, ARRAY [1], 5),
    (3, 2, 3, 3, '09:00:00', '10:20:00', NULL, ARRAY [3], 5),
    (3, 2, 3, 3, '10:35:00', '11:55:00', NULL, ARRAY [2, 4], 5),
    (3, 2, 3, 3, '12:25:00', '13:45:00', NULL, ARRAY [1], 5),
    (1, 2, 3, 1, '10:35:00', '11:55:00', NULL, ARRAY [1, 2, 3, 4], 6),
-- Снисаренко
    (5, 3, 2, 1, '17:25:00', '18:45:00', NULL, ARRAY [1, 2, 3, 4], 1),
    (4, 3, 2, 1, '17:25:00', '18:45:00', NULL, ARRAY [2, 4], 4),
-- Гриценко
    (7, 4, 5, 3, '15:50:00', '17:10:00', NULL, ARRAY [1, 3], 3),
    (7, 4, 5, 3, '17:25:00', '18:45:00', NULL, ARRAY [1, 3], 3),
    (2, 4, 5, 3, '12:25:00', '13:45:00', NULL, ARRAY [2], 5),
-- Жвакина
    (2, 5, 6, 3, '15:50:00', '17:10:00', NULL, ARRAY [2, 4], 1),
    (5, 5, 6, 1, '19:00:00', '20:20:00', NULL, ARRAY [1, 3, 4], 1),
    (2, 5, 6, 3, '15:50:00', '17:10:00', NULL, ARRAY [1, 3], 3),
    (2, 5, 6, 3, '17:25:00', '18:45:00', NULL, ARRAY [1, 3], 3),
-- Калиновская
    (10, 6, 1, 2, '15:50:00', '17:10:00', NULL, ARRAY [4], 3),
    (3, 6, 1, 2, '14:00:00', '15:20:00', NULL, ARRAY [1, 3], 5),
-- Плиска
    (7, 7, 4, 3, '12:25:00', '13:45:00', NULL, ARRAY [2, 4], 3),
    (7, 7, 4, 3, '14:00:00', '15:20:00', NULL, ARRAY [2, 4], 3),
    (2, 7, 4, 3, '09:00:00', '10:20:00', NULL, ARRAY [1], 5),
    (2, 7, 4, 3, '09:00:00', '10:20:00', NULL, ARRAY [3], 5),
    (2, 7, 4, 3, '10:35:00', '11:55:00', NULL, ARRAY [1, 3], 5),
-- Горюшкин
    (5, 8, 7, 1, '15:50:00', '17:10:00', NULL, ARRAY [1, 3], 2),
-- Марков
    (4, 9, 1, 1, '17:25:00', '18:45:00', NULL, ARRAY [1, 3], 4),
-- Чайкин
    (7, 10, 2, 2, '09:00:00', '10:20:00', NULL, ARRAY [2, 4], 5),
    (8, 10, 2, 2, '12:25:00', '13:45:00', NULL, ARRAY [3], 5),
    (2, 10, 2, 2, '12:25:00', '13:45:00', NULL, ARRAY [4], 5),
-- Сиротко
    (5, 11, 5, 1, '20:40:00', '22:00:00', NULL, ARRAY [1, 2, 3], 1)
;

INSERT INTO "group" (number, course, faculty_id)
VALUES (153504, 3, 1);

INSERT INTO class_group(class_id, group_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1),
       (29, 1),
       (30, 1),
       (31, 1),
       (32, 1);

INSERT INTO schedule (group_id, start, "end")
VALUES (1, '2023-09-01', '2023-12-15');


INSERT INTO class(classroom_id, teacher_id, subject_id, type_id, start_time, comment, week_numbers, day_of_week)
        values (5, 1, 4, 1, '19:00:00', NULL, ARRAY [2], 1);



