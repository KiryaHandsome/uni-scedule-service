package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Faculty;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyMapper implements RowMapper<Faculty> {

    @Override
    public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Faculty.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .shortName(rs.getString("short_name"))
                .build();
    }
}
