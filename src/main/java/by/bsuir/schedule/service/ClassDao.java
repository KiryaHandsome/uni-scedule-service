package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.ClassRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Class;
import by.bsuir.schedule.service.mapper.ClassMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(ClassRequest request) {
        String sql = """
                insert into class(
                    classroom_id,
                    teacher_id,
                    subject_id,
                    type_id,
                    start_time,
                    end_time,
                    comment,
                    week_numbers,
                    day_of_week
                )
                values(?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, request.getClassroomId());
            pss.setInt(2, request.getTeacherId());
            pss.setInt(3, request.getSubjectId());
            pss.setInt(4, request.getTypeId());
            pss.setTime(5, request.getStartTime());
            pss.setTime(6, request.getEndTime());
            pss.setString(7, request.getComment());
            pss.setInt(9, request.getDayOfWeek());
        });
    }

    public Class findById(int id) {
        String sql = Queries.SELECT_CLASSES + "\nwhere class_id=?;";
        List<Class> result = jdbcTemplate.query(sql, new Object[]{id}, new ClassMapper());
        if (result.isEmpty()) {
            throw new RestException(
                    String.format("Class with id=%d not found.", id),
                    HttpStatus.NOT_FOUND
            );
        }
        return result.get(0);
    }

    public void delete(int id) {
        String sql = """
                delete from class
                where id = ?;
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, id);
        });
    }

    public void update(int id, ClassRequest request) {
        String sql = """
                UPDATE class
                SET
                  classroom_id = ?,
                  teacher_id = ?,
                  subject_id = ?,
                  type_id = ?,
                  start_time = ?,
                  end_time = ?,
                  comment = ?,
                  week_numbers = ?,
                  day_of_week = ?
                WHERE
                  id = ?;
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, request.getClassroomId());
            pss.setInt(2, request.getTeacherId());
            pss.setInt(3, request.getSubjectId());
            pss.setInt(4, request.getTypeId());
            pss.setTime(5, request.getStartTime());
            pss.setTime(6, request.getEndTime());
            pss.setString(7, request.getComment());
            pss.setInt(9, request.getDayOfWeek());
            pss.setInt(10, id);
        });
    }
}
