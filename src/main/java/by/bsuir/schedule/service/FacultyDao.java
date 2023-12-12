package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.FacultyRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Faculty;
import by.bsuir.schedule.service.mapper.FacultyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacultyDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(FacultyRequest request) {
        String sql = """
                insert into faculty(name, short_name)
                values(?, ?);
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setString(1, request.getName());
            pss.setString(2, request.getShortName());
        });
    }

    public Faculty findById(int id) {
        String sql = """
                select *
                from faculty
                where id = ?;
                """;
        List<Faculty> result = jdbcTemplate.query(sql, new Object[]{id}, new FacultyMapper());
        if (result.isEmpty()) {
            throw new RestException(
                    String.format("Faculty with id=%d not found.", id),
                    HttpStatus.NOT_FOUND
            );
        }
        return result.get(0);
    }

    public void delete(int id) {
        String sql = """
                delete from faculty
                where id = ?;
                """;
        jdbcTemplate.update(sql, pss -> pss.setInt(1, id));
    }
}
