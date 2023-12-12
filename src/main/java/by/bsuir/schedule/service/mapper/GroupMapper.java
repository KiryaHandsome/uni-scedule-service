package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Faculty;
import by.bsuir.schedule.model.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Group.builder()
                .id(rs.getInt("id"))
                .course(rs.getInt("course"))
                .number(rs.getInt("number"))
                .faculty(Faculty.builder()
                        .id(rs.getInt("faculty_id"))
                        .build())
                .build();
    }
}
