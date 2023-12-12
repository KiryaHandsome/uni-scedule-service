package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.GroupRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Group;
import by.bsuir.schedule.service.mapper.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(GroupRequest request) {
        String sql = """
                insert into "group"(number, course, faculty_id)
                values(?, ?, ?);
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, request.getNumber());
            pss.setInt(2, request.getCourse());
            pss.setInt(3, request.getFacultyId());
        });
    }

    public Group findById(int id) {
        String sql = """
                select *
                from "group"
                where id = ?;
                """;
        List<Group> result = jdbcTemplate.query(sql, new Object[]{id}, new GroupMapper());
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
                delete from "group"
                where id = ?;
                """;
        jdbcTemplate.update(sql, pss -> pss.setInt(1, id));
    }
}
