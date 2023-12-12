package by.bsuir.schedule.service.mapper;

import by.bsuir.schedule.model.User;
import by.bsuir.schedule.service.Roles;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .role(Roles.getRoleById(rs.getInt("role_id") - 1))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .build();
    }
}
