package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Teacher.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("user_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .patronymic(rs.getString("patronymic"))
                .build();
    }
}
