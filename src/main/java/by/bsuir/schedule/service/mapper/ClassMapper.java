package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Class;
import by.bsuir.schedule.model.ClassType;
import by.bsuir.schedule.model.Classroom;
import by.bsuir.schedule.model.Subject;
import by.bsuir.schedule.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassMapper implements RowMapper<Class> {

    @Override
    public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Class.builder()
                .id(rs.getInt("class_id"))
                .comment(rs.getString("comment"))
                .subject(Subject.builder()
                        .id(rs.getInt("subject_id"))
                        .name(rs.getString("subject_name"))
                        .shortName(rs.getString("subject_short_name"))
                        .build())
                .classType(ClassType.builder()
                        .id(rs.getInt("classtype_id"))
                        .type(rs.getString("classtype_type"))
                        .shortType(rs.getString("classtype_short_type"))
                        .build())
                .teacher(Teacher.builder()
                        .id(rs.getInt("teacher_id"))
                        .firstName(rs.getString("teacher_first_name"))
                        .lastName(rs.getString("teacher_last_name"))
                        .patronymic(rs.getString("teacher_patronymic"))
                        .build())
                .classroom(Classroom.builder()
                        .id(rs.getInt("classroom_id"))
                        .building(rs.getInt("classroom_number"))
                        .number(rs.getInt("classroom_building"))
                        .build())
                .startTime(rs.getTime("start_time"))
                .endTime(rs.getTime("end_time"))
                .build();
    }
}
