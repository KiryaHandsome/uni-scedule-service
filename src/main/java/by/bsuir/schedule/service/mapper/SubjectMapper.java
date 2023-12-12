package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<Subject> {

    @Override
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Subject.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .shortName(rs.getString("short_name"))
                .build();
    }
}
