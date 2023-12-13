package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Class;
import by.bsuir.schedule.model.ClassType;
import by.bsuir.schedule.model.Classroom;
import by.bsuir.schedule.model.Subject;
import by.bsuir.schedule.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowClassMapper implements RowMapper<Class> {

    @Override
    public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Class.builder()
                .id(rs.getInt("class_id"))
                .comment(rs.getString("comment"))
                .subject(Subject.builder()
                        .id(rs.getInt("subject_id"))
                        .build())
                .classType(ClassType.builder()
                        .id(rs.getInt("classtype_id"))
                        .build())
                .teacher(Teacher.builder()
                        .id(rs.getInt("teacher_id"))
                        .build())
                .classroom(Classroom.builder()
                        .id(rs.getInt("classroom_id"))
                        .build())
                .startTime(rs.getTime("start_time"))
                .endTime(rs.getTime("end_time"))
                .build();
    }
}
