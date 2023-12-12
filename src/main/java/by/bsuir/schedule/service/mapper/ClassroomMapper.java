package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Classroom;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomMapper implements RowMapper<Classroom> {

    @Override
    public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Classroom.builder()
                .number(rs.getInt("number"))
                .building(rs.getInt("building"))
                .build();
    }
}
