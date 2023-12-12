package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.TeacherRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Teacher;
import by.bsuir.schedule.service.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(TeacherRequest request) {
        String sql = """
                insert into teacher(user_id, first_name, last_name, patronymic)
                values(?, ?, ?, ?);
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, request.getUserId());
            pss.setString(2, request.getFirstName());
            pss.setString(3, request.getLastName());
            pss.setString(4, request.getPatronymic());
        });
    }

    public Teacher findById(int id) {
        String sql = """
                select *
                from teacher
                where id = ?;
                """;
        List<Teacher> result = jdbcTemplate.query(sql, new Object[]{id}, new TeacherMapper());
        if (result.isEmpty()) {
            throw new RestException(
                    String.format("Teacher with id=%d not found.", id),
                    HttpStatus.NOT_FOUND
            );
        }
        return result.get(0);
    }

    public void delete(int id) {
        String sql = """
                delete from teacher
                where id = ?;
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, id);
        });
    }
}
