package by.bsuir.schedule.service;

public interface Queries {

    String SELECT_CLASSES = """
            SELECT DISTINCT cl.id          as class_id,
                            s.id           as subject_id,
                            s.name         as subject_name,
                            s.short_name   as subject_short_name,
                            cl.comment     as comment,
                            cl.start_time  as start_time,
                            cl.end_time    as end_time,
                            cl.day_of_week as day_of_week,
                            cl.week_numbers,
                            t.id           as teacher_id,
                            t.first_name   as teacher_first_name,
                            t.last_name    as teacher_last_name,
                            t.patronymic   as teacher_patronymic,
                            clt.id         as classtype_id,
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
            
            """;
    String CLASSES_FOR_GROUP_QUERY = SELECT_CLASSES + "WHERE gr.number = ?";
    String CLASSES_FOR_TEACHER_QUERY = SELECT_CLASSES +
            "WHERE CONCAT(t.first_name, ' ', t.last_name, ' ', t.patronymic) ILIKE '%' || ? || '%'";
}
