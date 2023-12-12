package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.Class;
import by.bsuir.schedule.model.Faculty;
import by.bsuir.schedule.model.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = Schedule.builder()
                .groupNumber(rs.getInt("group_number"))
                .startDate(rs.getDate("start_date"))
                .endDate(rs.getDate("end_date"))
                .faculty(Faculty.builder()
                        .id(rs.getInt("faculty_id"))
                        .name(rs.getString("faculty_name"))
                        .shortName(rs.getString("faculty_short_name"))
                        .build())
                .build();
        List<Class> classes = new ArrayList<>();
        ClassMapper classMapper = new ClassMapper();
        do {
            classes.add(classMapper.mapRow(rs, rowNum));
        } while (rs.next());
        schedule.setClasses(classes);
        return schedule;
    }
}
