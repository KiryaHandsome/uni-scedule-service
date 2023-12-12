package by.bsuir.schedule.service;

import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Schedule;
import by.bsuir.schedule.service.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScheduleDao {

    private final JdbcTemplate jdbcTemplate;

    public Schedule findScheduleByGroupNumber(int groupNumber) {
        return jdbcTemplate.query(Queries.CLASSES_FOR_GROUP_QUERY, new Object[]{groupNumber}, rs -> {
            rs.next();
            return new ScheduleMapper().mapRow(rs, 0);
        });
    }

    public Schedule findScheduleByTeacherName(String name) {
        return jdbcTemplate.query(Queries.CLASSES_FOR_TEACHER_QUERY, new Object[]{name}, rs -> {
            if (rs.next()) {
                return new ScheduleMapper().mapRow(rs, 0);
            }
            throw new RestException(
                    String.format("Schedule for teacher name %s not found", name),
                    HttpStatus.NOT_FOUND
            );
        });
    }
}
