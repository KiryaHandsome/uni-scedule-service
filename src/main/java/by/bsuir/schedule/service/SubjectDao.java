package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.SubjectRequest;
import by.bsuir.schedule.dto.UserRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Subject;
import by.bsuir.schedule.model.User;
import by.bsuir.schedule.service.mapper.SubjectMapper;
import by.bsuir.schedule.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(SubjectRequest request) {
        String sql = """
                insert into subject(name, short_name)
                values(?, ?);
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setString(1, request.getName());
            pss.setString(2, request.getShortName());
        });
    }

    public Subject findById(int id) {
        String sql = """
                select *
                from subject
                where id = ?;
                """;
        List<Subject> result = jdbcTemplate.query(sql, new Object[]{id}, new SubjectMapper());
        if (result.isEmpty()) {
            throw new RestException(
                    String.format("Subject with id=%d not found.", id),
                    HttpStatus.NOT_FOUND
            );
        }
        return result.get(0);
    }

    public void delete(int id) {
        String sql = """
                delete from subject
                where id = ?;
                """;
        jdbcTemplate.update(sql, pss -> pss.setInt(1, id));
    }
}
