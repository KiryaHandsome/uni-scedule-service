-- Выборка преподавателей и предметов, которые они ведут
SELECT teacher.first_name, teacher.patronymic, subject.name
FROM teacher
         LEFT JOIN teacher_subject ON teacher.id = teacher_subject.teacher_id
         LEFT JOIN subject ON teacher_subject.subject_id = subject.id;

-- Выборка занятий для конкретной группы

SELECT DISTINCT cl.id          as class_id,
                s.name         as subject_name,
                s.short_name   as subject_short_name,
                cl.comment     as comment,
                cl.start_time  as start_time,
                cl.end_time    as end_time,
                cl.day_of_week as day_of_week,
                cl.week_numbers,
                t.id           as teacher_id,
                t.first_name      teacher_first_name,
                t.last_name    as teacher_last_name,
                t.patronymic   as teacher_patronymic,
                clt.type       as classtype_type,
                clt.short_type as classtype_short_type,
                clr.id         as classroom_id,
                clr.number     as classroom_number,
                clr.building   as classroom_building,
                sch.start      as start_date,
                sch."end"      as end_date,
                gr.number      as group_number,
                f.id           as faculty_id,
                f.name         as faculty_name,
                f.short_name   as faculty_short_name
FROM class cl
         JOIN class_group cg ON cg.class_id = cl.id
         JOIN "group" gr ON gr.id = cg.group_id
         LEFT JOIN teacher t ON t.id = cl.teacher_id
         LEFT JOIN class_type clt ON clt.id = cl.type_id
         LEFT JOIN classroom clr ON clr.id = cl.classroom_id
         LEFT JOIN subject s ON s.id = cl.subject_id
         LEFT JOIN schedule sch ON sch.group_id = gr.id
         LEFT JOIN faculty f ON f.id = gr.faculty_id
WHERE CONCAT(t.first_name, ' ', t.last_name, ' ', t.patronymic) ILIKE  '%a%';
-- WHERE gr.number = ?;

SELECT DISTINCT cl.id          as class_id,
                s.name         as subject_name,
                s.short_name   as subject_short_name,
                cl.comment     as comment,
                cl.start_time  as start_time,
                cl.end_time    as end_time,
                cl.day_of_week as day_of_week,
                cl.week_numbers,
                t.id           as teacher_id,
                t.first_name      teacher_first_name,
                t.last_name    as teacher_last_name,
                t.patronymic   as teacher_patronymic,
                clt.type       as classtype_type,
                clt.short_type as classtype_short_type,
                clr.id         as classroom_id,
                clr.number     as classroom_number,
                clr.building   as classroom_building,
                sch.start      as start_date,
                sch.end        as end_date,
                gr.number      as group_number,
                f.id           as faculty_id,
                f.name         as faculty_name,
                f.short_name   as faculty_short_name
FROM class cl
         JOIN class_group cg ON cg.class_id = cl.id
         JOIN "group" gr ON gr.id = cg.group_id
         LEFT JOIN teacher t ON t.id = cl.teacher_id
         LEFT JOIN class_type clt ON clt.id = cl.type_id
         LEFT JOIN classroom clr ON clr.id = cl.classroom_id
         LEFT JOIN subject s ON s.id = cl.subject_id
         LEFT JOIN schedule sch ON sch.group_id = gr.id
         LEFT JOIN faculty f ON f.id = gr.faculty_id
where gr.number = ?;

SELECT DISTINCT cl.id,
                gr.number,
                s.name,
                s.short_name,
                cl.comment,
                cl.start_time,
                cl.end_time,
                cl.day_of_week,
                cl.week_numbers,
                t.last_name,
                t.first_name,
                t.patronymic,
                clt.short_type,
                clr.number,
                clr.building
FROM class cl
         JOIN class_group cg ON cg.class_id = cl.id
         JOIN "group" gr ON gr.id = cg.group_id
         LEFT JOIN teacher t ON t.id = cl.teacher_id
         LEFT JOIN class_type clt ON clt.id = cl.type_id
         LEFT JOIN classroom clr ON clr.id = cl.classroom_id
         LEFT JOIN subject s ON s.id = cl.subject_id
WHERE gr.id = ?;

-- Выборка занятий для преподавателя по вхождению в полное имя
SELECT *
FROM (SELECT t.last_name || ' ' || t.first_name || ' ' || t.patronymic AS teacher_full_name,
             cl.start_time,
             cl.end_time,
             cl.week_numbers,
             cl.classroom_id,
             cl.day_of_week,
             cl.comment,
             clt.short_type,
             clr.number,
             clr.building
      FROM class cl
               JOIN teacher t ON t.id = cl.teacher_id
               LEFT JOIN classroom clr ON clr.id = cl.classroom_id
               LEFT JOIN class_type clt ON clt.id = cl.type_id) AS subquery
WHERE teacher_full_name ILIKE '%влад%';

SELECT *
FROM classroom;

SELECT *
FROM teacher;

SELECT *
FROM "group";

DELETE
FROM classroom cr
WHERE cr.id = ?;

DELETE
FROM teacher
WHERE teacher.id = 1;

DELETE
FROM subject
WHERE subject.id = ?;

-- Обновление/Добавление комментария к занятию
UPDATE teacher
SET first_name = 'Slava'
WHERE id = 2;

INSERT INTO class (classroom_id, teacher_id, subject_id, type_id, start_time, comment, week_numbers,
                   day_of_week)
VALUES (5, 1, 4, 1, '19:00:00', NULL, ARRAY [2], 1);

explain
select *
from class
where end_time > '19:00';

select distinct t.patronymic
from teacher as t
where patronymic LIKE '%ч'
order by t.patronymic desc
limit 4 offset 2
;

explain analyse
select t.last_name, g.number, s.name
from teacher t
         join class c on c.teacher_id = t.id
         join class_group cg on cg.class_id = c.id
         join "group" g on g.id = cg.group_id
         join subject s on s.id = c.subject_id;

select
    id as class_id,
    classroom_id,
    teacher_id,
    subject_id,
    type_id,
    start_time,
    end_time,
    comment,
    week_numbers,
    day_of_week
from class
where id = ?;
