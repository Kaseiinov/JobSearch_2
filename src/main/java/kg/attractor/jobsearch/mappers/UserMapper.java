package kg.attractor.jobsearch.mappers;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setAge(rs.getInt("age"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setAvatar(rs.getString("avatar"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setAccountType(rs.getString("account_type"));
        return user;
    }
}
