package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.ClassroomRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.Classroom;
import by.bsuir.schedule.service.mapper.ClassroomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassroomDao {

    private final JdbcTemplate jdbcTemplate;

    public void create(ClassroomRequest request) {
        String sql = """
                insert into classroom(number, building)
                values(?, ?);
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, request.getNumber());
            pss.setInt(2, request.getBuilding());
        });
    }

    public Classroom findById(int id) {
        String sql = """
                select *
                from classroom
                where id = ?;
                """;
        List<Classroom> result = jdbcTemplate.query(sql, new Object[]{id}, new ClassroomMapper());
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
                delete from classroom
                where id = ?;
                """;
        jdbcTemplate.update(sql, pss -> pss.setInt(1, id));
    }
}
