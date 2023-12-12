package by.bsuir.schedule.service;

import by.bsuir.schedule.dto.UserRequest;
import by.bsuir.schedule.exception.RestException;
import by.bsuir.schedule.model.User;
import by.bsuir.schedule.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public void create(UserRequest request) {
        String sql = """
                insert into "user"(role_id, email, password)
                values(?, ?, ?);
                """;
        jdbcTemplate.update(sql, pss -> {
            pss.setInt(1, request.getRoleId());
            pss.setString(2, request.getEmail());
            pss.setString(3, passwordEncoder.encode(request.getPassword()));
        });
    }

    public User findById(int id) {
        String sql = """
                select *
                from "user"
                where id = ?;
                """;
        List<User> result = jdbcTemplate.query(sql, new Object[]{id}, new UserMapper());
        if (result.isEmpty()) {
            throw new RestException(
                    String.format("User with id=%d not found.", id),
                    HttpStatus.NOT_FOUND
            );
        }
        return result.get(0);
    }

    public void delete(int id) {
        String sql = """
                delete from "user"
                where id = ?;
                """;
        jdbcTemplate.update(sql, pss -> pss.setInt(1, id));
    }

    public User findByEmail(String email) {
        String sql = """
                select *
                from "user"
                where email = ?;
                """;
        List<User> result = jdbcTemplate.query(sql, new Object[]{email}, new UserMapper());
        if (result.isEmpty()) {
            throw new RestException(
                    String.format("User with email=%s not found.", email),
                    HttpStatus.NOT_FOUND
            );
        }
        return result.get(0);
    }
}

